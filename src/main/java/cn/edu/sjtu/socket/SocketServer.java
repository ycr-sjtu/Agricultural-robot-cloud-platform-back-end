package cn.edu.sjtu.socket;

import cn.edu.sjtu.entity.Robot;
import cn.edu.sjtu.service.RobotService;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Component
@Slf4j
public class SocketServer {

    private static RobotService robotService;

    @Autowired
    public void setRobotService(RobotService robotService) {
        SocketServer.robotService = robotService;
    }

    // 解码buffer
    private Charset cs = StandardCharsets.UTF_8;

    // 接受数据缓冲区
    private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);

    // 发送数据缓冲区
    private static ByteBuffer rBuffer = ByteBuffer.allocate(1024);

    // 选择器
    private static Selector selector;

    /**
     * 启动socket服务，开启监听
     *
     * @param port
     */
    public void startSocketServer(int port) {
        try {
            // 打开通信通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            // 获取套接字
            ServerSocket serverSocket = serverSocketChannel.socket();
            // 绑定端口号
            serverSocket.bind(new InetSocketAddress(port));
            // 打开监听
            selector = Selector.open();
            // 将通信信道注册到监听器
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("正在监听" + port + "端口");
            // 监听器一直监听，如果客户端有请求就会进入响应的时间处理
            while (true) {
                selector.select(); // select()一直阻塞直到相关事件发生或超时
                Set<SelectionKey> selectionKeys = selector.selectedKeys(); // 监听到事件
                for (SelectionKey key : selectionKeys) {
                    handle(key);
                }
                selectionKeys.clear(); // 清除处理过的事件
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理不同事件
     *
     * @param selectionKey
     * @throws IOException
     */
    private void handle(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel socketChannel = null;
        String requestMsg = "";
        int count = 0;
        if (selectionKey.isAcceptable()) {
            // 每有客户端连接，即注册通信信道为可读
            serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("客户端连接成功");
        } else if (selectionKey.isReadable()) {
            socketChannel = (SocketChannel) selectionKey.channel();
            rBuffer.clear();
            count = socketChannel.read(rBuffer);
            // 读取数据
            if (count > 0) {
                rBuffer.flip();
                //requestMsg = String.valueOf(cs.decode(rBuffer).array());
                requestMsg = new String(rBuffer.array(),"GB2312").trim();
                saveData(requestMsg);
            }
            // 返回数据
//            String responseMsg = "客户端消息：" + requestMsg;
//			sBuffer = ByteBuffer.allocate(responseMsg.getBytes().length);
//			sBuffer.put(responseMsg.getBytes("GB2312"));
//			sBuffer.flip();
//			socketChannel.write(sBuffer);
//            sBuffer.clear();
			//socketChannel.close();
        }
    }

    /**
     * 持久化
     * @param msg
     */
    private void saveData(String msg) {
        System.out.println("接收到消息：" + msg);
        // 校验json格式
        if (JSON.isValidObject(msg)) {
            try {
                Robot robot = new Robot();
                JSONObject object = JSONObject.parseObject(msg);
                object.forEach((k,v)->{
                    if(k.equals("lng")){
                        robot.setLng(Double.valueOf(v.toString()));
                    } else if (k.equals("lat")) {
                        robot.setLat(Double.valueOf(v.toString()));
                    } else if (k.equals("url")) {
                        robot.setUrl(v.toString());
                    }
                });
                if (robot.getLng() != null && robot.getLat() != null) {
                    robot.setCategory("未知类型");
                    robot.setRobotNumber("未知型号");
                    robot.setTime(new Date());
                    robotService.save(robot);
                }
            } catch (JSONException e) {
                log.info("格式错误");
            }
        }
    }
}
