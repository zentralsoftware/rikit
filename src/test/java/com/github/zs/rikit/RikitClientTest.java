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
	
	@Test
	public void send() throws RikitException, URISyntaxException
	{
		URI destinationURI = new URI("ws://localhost:8080/rikit");
		RikitClient client = new RikitClient(destinationURI);
		client.send("test");
		client.bye();
	}	

	@Test
	public void multipleClientTest() throws RikitException, URISyntaxException
	{
		URI destinationURI = new URI("ws://localhost:8080/rikit");
		RikitClient client1 = new RikitClient(destinationURI);
		RikitClient client2 = new RikitClient(destinationURI);
		client1.ping();
		client1.send("test from client1");
		client2.ping();
		client2.send("test from client2");
		client1.bye();
		client2.bye();
	}
	
	@AfterClass
	public static void destroy() throws RikitException
	{
		daemon.stop();
	}
}
