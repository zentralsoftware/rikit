package com.github.zs.rikit;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class RikitDaemon {

	final static Logger logger = LoggerFactory.getLogger(RikitDaemon.class);
	private Config config;
	
	public RikitDaemon()
	{
		config = ConfigFactory.load();
	}
	
	public RikitDaemon(String configName)
	{
		config = ConfigFactory.load(configName);
	}	
	
	public void run() throws RikitException
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
	
	private void startRoot() throws RikitException
	{
		Rikit rikit = new Rikit();
		rikit.create();
	}
	
	private void startMember() throws RikitException
	{
		Rikit rikit = new Rikit();
		rikit.join();
	}	
	
    public static void main(String[] args) throws Exception {
    	String configName;
    	RikitDaemon daemon = null;
    	if (args.length == 1)
    	{
    		configName = args[0];
    		daemon = new RikitDaemon(configName);
    	} else
    	{
    		daemon = new RikitDaemon();
    	}
    	daemon.run();
    }
}
