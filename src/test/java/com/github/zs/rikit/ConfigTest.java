package com.github.zs.rikit;

import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;

public class ConfigTest {

	private Config config;
	private Config config2;	
	
	@Before
	public void setup()
	{
		config = ConfigFactory.load();
		config2 = ConfigFactory.load("rikit2");		
	}
	
	@Test
	public void listConfig()
	{
		for (Entry<String, ConfigValue> entry : config.entrySet())
		{
			System.err.println(entry.getKey() + ":" + entry.getValue());
		}
		for (Entry<String, ConfigValue> entry : config2.entrySet())
		{
			System.err.println(entry.getKey() + ":" + entry.getValue());
		}		
	}
	
	@Test
	public void showKeyValue()
	{
		String val = config.getString("rikit.hostname");
		int port = config.getInt("rikit.port");
		Assert.assertEquals("localhost", val);
		Assert.assertEquals(8080, port);
		
		val = config2.getString("rikit.hostname");
		port = config2.getInt("rikit.port");
		Assert.assertEquals("localhost", val);		
		Assert.assertEquals(8081, port);		
	}
	

}
