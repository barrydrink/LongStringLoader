package com.penrillian.longstringloader;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

public class LongStringLoaderExceptionTest
{
	@Test
	public void testGetLocalizedMessage()
	{
		LongStringLoaderException ex = new LongStringLoaderException("a message");

		Assert.assertEquals("a message", ex.getLocalizedMessage());
	}

}