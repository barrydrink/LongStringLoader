package com.penrillian.longstringloader;

import junit.framework.Assert;

import org.junit.Test;

public class LongStringLoaderExceptionTest
{
	@Test
	public void testGetLocalizedMessage()
	{
		LongStringLoaderException ex = new LongStringLoaderException("a message");

		Assert.assertEquals("a message", ex.getLocalizedMessage());
	}

}