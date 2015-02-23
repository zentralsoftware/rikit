package com.github.zs.rikit;


import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

public class IdentifierTest {

	@Test
	public void simpleIdentifier()
	{
		StringIdentity ident1 = new StringIdentity("identity1");
		Identifier<StringIdentity> identifier1 = new Identifier<StringIdentity>(ident1);
		StringIdentity ident2 = new StringIdentity("identity2");
		Identifier<StringIdentity> identifier2 = new Identifier<StringIdentity>(ident2);		
		System.err.println(identifier1);
		System.err.println(identifier2);
		System.err.println(identifier1.compareTo(identifier2));
	}
	
	@Test
	public void testURI() throws URISyntaxException
	{
		URI uri = new URI("ws://localhost:8080/rikit");
		System.err.println(uri.getScheme());
		System.err.println(uri.getRawSchemeSpecificPart());
	}
	
	@Test
	public void rikitIdentityTest()
	{
		RikitIdentity rikitIdentity1 = new RikitIdentity("localhost", 8081);
		Identifier<RikitIdentity> identifier1 = new Identifier<RikitIdentity>(rikitIdentity1);
		RikitIdentity rikitIdentity2 = new RikitIdentity("localhost", 8082);
		Identifier<RikitIdentity> identifier2 = new Identifier<RikitIdentity>(rikitIdentity2);
		Assert.assertEquals(20, identifier1.getId().length);
		Assert.assertEquals(20, identifier2.getId().length);
		Assert.assertEquals("6EDC84FFBB1C9C250094D78383DD5BF71C5C7A02", identifier1.toString());
		Assert.assertEquals("2C510900D7D127C18B1D0C9544CE0D775FC3E672",identifier2.toString());
		System.err.println(identifier1.compareTo(identifier2));
	}
}
