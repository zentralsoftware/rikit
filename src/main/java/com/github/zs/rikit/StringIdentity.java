package com.github.zs.rikit;

public class StringIdentity implements Identifiable{

	private String id;
	public StringIdentity(String id)
	{
		this.id = id;
	}
	
	@Override
	public byte[] generateId() {
		return id.getBytes();
	}

}
