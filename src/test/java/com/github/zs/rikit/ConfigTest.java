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
	
	@Before
	public void setup()
	{
		config = ConfigFactory.load();
	}
	
	@Test
	public void listConfig()
	{
		for (Entry<String, ConfigValue> entry : config.entrySet())
		{
			System.err.println(entry.getKey() + ":" + entry.getValue());
		}
	}
	
	@Test
	public void showKeyValue()
	{
		String val = config.getString("hostname");
		Assert.assertEquals("localhost", val);
	}
	

}
