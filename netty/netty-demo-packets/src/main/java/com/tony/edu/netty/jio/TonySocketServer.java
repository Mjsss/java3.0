package com.tony.edu.netty.jio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TonySocketServer {
    public static void main(String[] args) throws IOException, Exception {
        // server --- jvm ===操作系统 申请端口
        ServerSocket serverSocket = new ServerSocket(9999);
        // 获取新连接
        while (true) {
            final Socket accept = serverSocket.accept();
            // accept.getOutputStream().write("推送实例");
            InputStream inputStream = accept.getInputStream();
            while (true) { // 接下来，为了完善业务，一堆的代码要去写 --0--- 拆包粘包
                byte[] request = new byte[1024];
                int read = inputStream.read(request);
                if (read == -1) {
                    break;
                }

                // 得到请求内容，解析，得到发送对象和发送内容（）
                String content = new String(request); // 可能是一次完整的数据包， 也可能是多个数据包，甚至是不完整的数据
                // 每次读取到的数据，不能够保证是一条完整的信息
//                System.out.println(content);

                // TODO 收到数据根据 协议 去解析数据
                String roomId = content.substring(0, 10);
                String msg = content.replace(roomId, "");

                RoomMsg roomMsg = new RoomMsg();
                roomMsg.roomId = roomId;
                roomMsg.msg = msg;
                System.out.println(roomMsg);
            }
        }
    }
}
class RoomMsg{
    String roomId;
    String msg;

    @Override
    public String toString() {
        return "RoomMsg{" +
                "roomId='" + roomId + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
