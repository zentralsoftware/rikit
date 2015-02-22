package com.github.zs.rikit;

import org.junit.Assert;
import org.junit.Test;

public class RikitDaemonTest {

	@Test
	public void testRun() throws RikitException, InterruptedException
	{
		Bootstrap daemon = new Bootstrap();
		daemon.start();
		Assert.assertNotNull(daemon);
		System.err.println("daemon started");
		Thread.sleep(10*1000);		
		daemon.stop();
		System.err.println("daemon stopped");
	}
}
