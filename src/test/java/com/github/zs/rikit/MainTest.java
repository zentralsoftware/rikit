package com.github.zs.rikit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetAddress;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class MainTest {

    public static void main(String[] args) throws Exception {
    	
        Config config = ConfigFactory.load();
        String hostname = config.getString("rikit.hostname");
        int port = config.getInt("rikit.port");
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .handler(new LoggingHandler(LogLevel.INFO))
             .childHandler(new RikitInitializer());

            InetAddress inetAddress = InetAddress.getByName(hostname);
            Channel ch = b.bind(inetAddress,port).sync().channel();

            System.out.println("started");

            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }	
}
