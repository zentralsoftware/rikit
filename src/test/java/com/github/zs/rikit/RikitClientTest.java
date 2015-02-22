package com.github.zs.rikit;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RikitClientTest {

	private static Bootstrap daemon = new Bootstrap();
	
	@BeforeClass
	public static void init() throws RikitException
	{
		daemon.start();
	}
	
	@Test
	public void ping() throws RikitException, URISyntaxException
	{
		URI destinationURI = new URI("ws://localhost:8080/rikit");
		RikitClient client = new RikitClient(destinationURI);
		client.ping();
		client.bye();
	}
	
	@AfterClass
	public static void destroy() throws RikitException
	{
		daemon.stop();
	}
}
