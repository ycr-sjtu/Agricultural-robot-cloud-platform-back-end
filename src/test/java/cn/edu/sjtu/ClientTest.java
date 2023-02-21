package cn.edu.sjtu;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientTest {
    public static void main(String[]args) throws IOException {
        Socket sock = new Socket("106.15.233.31",8081);
        //建立连接后就可以往服务端写数据了
        Writer writer = new OutputStreamWriter(sock.getOutputStream(), StandardCharsets.UTF_8);
        writer.write("{\"lng\": 120, \"lat\": 30}");
//		      writer.write("eof\n");
        writer.flush();
        writer.close();
        sock.close();
        System.out.println("disconnected.");
    }
}
