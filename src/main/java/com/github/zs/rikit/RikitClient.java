package com.github.zs.rikit;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RikitClient {
	
	final static Logger logger = LoggerFactory.getLogger(RikitClient.class);
	private static EventLoopGroup group = new NioEventLoopGroup();
	private URI destinationURI;
	private Channel channel;
	
	public RikitClient(URI destinationURI) throws RikitException
	{
		this.destinationURI = destinationURI;
		init();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run()
			{
				logger.info("Shutdown client event loop");
				group.shutdownGracefully();
			}
		});
	}
	
	private void init() throws RikitException
	{
		try {
			final WebSocketClientHandler handler = new WebSocketClientHandler(
			        WebSocketClientHandshakerFactory.newHandshaker(
			        		this.destinationURI, WebSocketVersion.V13, null, false, new DefaultHttpHeaders()));
	        Bootstrap b = new Bootstrap();
	        b.group(group)
	         .channel(NioSocketChannel.class)
	         .handler(new ChannelInitializer<SocketChannel>() {
	             @Override
	             protected void initChannel(SocketChannel ch) {
	                 ChannelPipeline p = ch.pipeline();
	                 p.addLast(
	                         new HttpClientCodec(),
	                         new HttpObjectAggregator(8192),
	                         handler);
	             }
	         });				
	        
	        
            this.channel = b.connect(this.destinationURI.getHost(), this.destinationURI.getPort()).sync().channel();
            handler.handshakeFuture().sync();
            
		} catch (InterruptedException e) {
			throw new RikitException(RikitErrorCode.IO, e);
		}	
	}
	
	public void ping()
	{
        WebSocketFrame frame = new PingWebSocketFrame(Unpooled.wrappedBuffer(new byte[] { 8, 1, 8, 1 }));	
        this.channel.writeAndFlush(frame);
	}
	
	public void send(String message)
	{
		WebSocketFrame frame = new TextWebSocketFrame(message);
        this.channel.writeAndFlush(frame);
	}
	
	public void bye() throws RikitException
	{
		this.channel.writeAndFlush(new CloseWebSocketFrame());
		try {
			this.channel.closeFuture().sync();
		} catch (InterruptedException e) {
			throw new RikitException(RikitErrorCode.IO, e);
		}
	}
}
