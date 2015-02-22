package com.github.zs.rikit;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Bootstrap {

	final static Logger logger = LoggerFactory.getLogger(Bootstrap.class);
	private Config config;
	private Rikit rikit;
	
	public Bootstrap()
	{
		config = ConfigFactory.load();
		rikit = new Rikit(config);
	}
	
	public Bootstrap(String configName)
	{
		config = ConfigFactory.load(configName);
		rikit = new Rikit(config);		
	}	
	
	public void start() throws RikitException
	{
		RikitMode mode = RikitMode.valueOf(config.getString(StringResource.CONFIG_MODE));
		switch (mode)
		{
			case ROOT:
				startRoot();
				break;
			case MEMBER:
				startMember();
				break;
			default: throw new RikitException(RikitErrorCode.IO, new IOException("Mode " + mode + "not found"));
		}
	}
	
	public void stop() throws RikitException
	{
		rikit.shutdown();
	}
	
	private void startRoot() throws RikitException
	{
		this.rikit.create();
	}
	
	private void startMember() throws RikitException
	{
		this.rikit.join();
	}	
	
    public static void main(String[] args) throws Exception {
    	String configName;
    	Bootstrap daemon = null;
    	if (args.length == 1)
    	{
    		configName = args[0];
    		daemon = new Bootstrap(configName);
    	} else
    	{
    		daemon = new Bootstrap();
    	}
    	daemon.start();
    }
}
