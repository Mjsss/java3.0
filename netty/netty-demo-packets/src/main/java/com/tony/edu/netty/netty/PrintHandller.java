package com.tony.edu.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 后续处理handdler
 */
public class PrintHandller extends SimpleChannelInboundHandler {// ChannelInboundHandlerAdapter {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 输出 bytebuf -- 收到一条完整的消息，不能够是被拆包或者粘包
        ByteBuf buf = (ByteBuf) msg;
        byte[] contentByte = new byte[buf.readableBytes()];
        buf.readBytes(contentByte);
        String content = new String(contentByte);
        // TODO 收到数据根据 协议 去解析数据
        String roomId = content.substring(0, 10);
        String message = content.replace(roomId, "");

        RoomMsg roomMsg = new RoomMsg();
        roomMsg.roomId = roomId;
        roomMsg.msg = message;
        System.out.println(roomMsg);
//        System.out.println(Thread.currentThread() + ": 最终打印" + new String(content));
    }

    // 异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
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
