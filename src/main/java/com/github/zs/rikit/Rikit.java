package com.github.zs.rikit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.typesafe.config.Config;

public class Rikit implements RikitActivity, ReceiverHandler {
	
	final static Logger logger = LoggerFactory.getLogger(Rikit.class);
	private List<Rikit> predecessors = new ArrayList<Rikit>();	
	private List<Rikit> successors = new ArrayList<Rikit>();
	private Config config;
    private EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private EventLoopGroup workerGroup = new NioEventLoopGroup();
	private Channel daemonChannel;
	
	public Rikit(Config config)
	{
		this.config = config;
		init();
	}
	
	public List<Rikit> getPredecessors() {
		return predecessors;
	}

	public void setPredecessors(List<Rikit> predecessors) {
		this.predecessors = predecessors;
	}

	public List<Rikit> getSuccessors() {
		return successors;
	}

	public void setSuccessors(List<Rikit> successors) {
		this.successors = successors;
	}

	/**
	 * Create a new Chord ring
	 * @throws RikitException 
	 */
	@Override
	public void create() throws RikitException {
		this.predecessors.clear();
		this.successors.clear();
		this.successors.add(this);
		startDaemon();
	}

	/**
	 * Join a Chord ring containing a Rikit that addressed by bootstrapURL
	 */
	@Override
	public void join() throws RikitException 
	{
		
		startDaemon();
	}
	
	private void doJoin()
	{
		
	}

	@Override
	public void stabilize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inform() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fixFingers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkPredecessor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceived(String received) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findSuccessor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closestPrecedingNode() {
		// TODO Auto-generated method stub
		
	}

	private void startDaemon() throws RikitException
	{
        String hostname = config.getString(StringResource.CONFIG_HOSTNAME);
        int port = config.getInt(StringResource.CONFIG_PORT);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .handler(new LoggingHandler(LogLevel.INFO))
             .childHandler(new RikitInitializer());
            InetAddress inetAddress = InetAddress.getByName(hostname);
            daemonChannel = b.bind(inetAddress,port).sync().channel();
        } catch (UnknownHostException e) {
        	throw new RikitException(RikitErrorCode.NETWORK, e);
		} catch (InterruptedException e) {
        	throw new RikitException(RikitErrorCode.INTERNAL, e);
		} 				
	}
	
	public void shutdown() throws RikitException
	{
		daemonChannel.close();
		try {
			daemonChannel.closeFuture().sync();
		} catch (InterruptedException e) {
			throw new RikitException(RikitErrorCode.INTERNAL, e);
		}		
	}
	
	private void init()
	{
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run()
			{
				logger.info("Shutdown daemon event loop");
				bossGroup.shutdownGracefully();
				workerGroup.shutdownGracefully();
			}
		});
	}
}
