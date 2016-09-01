package com.penrillian.longstringloader;

import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LongStringLoaderTest
{
	@BeforeClass
	public static void setUp() {
		Looper.prepare();
	}

	@Test
	public void testCreateObject() throws Exception
	{
		LongStringLoader loader = new LongStringLoader(InstrumentationRegistry.getContext(), new LongStringLoadCompleteListener()
		{
			@Override
			public void onLicenceLoadComplete()
			{

			}
		}, null);

		Assert.assertNotNull(loader);
	}

//	@Test
//	public void testGetSplitString() throws Exception
//	{
//		InstrumentationRegistry.getContext();
//		String stringToSplit = "THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS\n" +
//				"\"AS IS\" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT\n" +
//				"LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR\n" +
//				"A PARTICULAR PURPOSE ARE DISCLAIMED.";
//
////		Iterable<String> splitString = new LongStringLoader()
//	}
}