package com.github.zs.rikit;

public class RikitFactory {

	private static Rikit localInstance;
	
	public static synchronized Rikit getLocalInstance()
	{
		if (localInstance == null)
		{
			localInstance = new Rikit();
		}
		return localInstance;
	}
}
