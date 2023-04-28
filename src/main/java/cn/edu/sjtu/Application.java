package cn.edu.sjtu;

import cn.edu.sjtu.socket.SocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@MapperScan("cn.edu.sjtu.mapper")
public class Application {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
        SocketServer server = new SocketServer();
        server.startSocketServer(8081);
    }

}
