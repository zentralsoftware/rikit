package com.github.zs.rikit;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

public class SHATest {

	@Test
	public void generateHash() throws NoSuchAlgorithmException
	{
		String endpoint = "The quick brown fox jumps over the lazy dog";
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(endpoint.getBytes());
		byte bytes[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte by:bytes)
		{
			sb.append(Integer.toHexString(0xff & by));
		}
		Assert.assertEquals(20, bytes.length);
		Assert.assertEquals("2fd4e1c67a2d28fced849ee1bb76e7391b93eb12", sb.toString());
	}
}
