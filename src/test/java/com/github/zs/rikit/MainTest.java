package com.github.zs.rikit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetAddress;

public class MainTest {

    public static void main(String[] args) throws Exception {
    	
    	 String ip = System.getenv("OPENSHIFT_INTERNAL_IP");
         if(ip == null) {
             ip = "localhost";
         }
         String ports = System.getenv("OPENSHIFT_INTERNAL_PORT");
         if(ports == null) {
             ports = "8080";
         }    	
    	
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .handler(new LoggingHandler(LogLevel.INFO))
             .childHandler(new RikitInitializer());

            InetAddress inetAddress = InetAddress.getByName(ip);
            int port = Integer.decode(ports);
            Channel ch = b.bind(inetAddress,port).sync().channel();

            System.out.println("started");

            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }	
}
