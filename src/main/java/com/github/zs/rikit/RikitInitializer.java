package com.github.zs.rikit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RikitInitializer extends ChannelInitializer<SocketChannel> {
	
	final static Logger logger = LoggerFactory.getLogger(RikitInitializer.class);
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		Rikit localInstance = RikitFactory.getLocalInstance();
		RikitReceiver receiver = new RikitReceiver();
		receiver.getHandlers().add(localInstance);
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(receiver);		
	}

}
