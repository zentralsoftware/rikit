package com.github.zs.rikit;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RikitIdentity implements Identifiable {

	final static Logger logger = LoggerFactory.getLogger(RikitIdentity.class);
	private String hostname;
	private int port;
	public RikitIdentity(String hostname, int port)
	{
		this.hostname = hostname;
		this.port = port;
	}	
	
	@Override
	public byte[] generateId() {
		byte bytes[] = null;
		StringBuffer sb = new StringBuffer();
		sb.append(this.hostname);
		sb.append(":");
		sb.append(this.port);
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(sb.toString().getBytes());
			bytes = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return bytes;
	}

}
