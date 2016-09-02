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
	private LongStringLoader loader;

	@BeforeClass
	public static void setUp() {
		Looper.prepare();
	}

	private LongStringLoader createLoaderWithSplitLength(int splitLength)
	{
		return new LongStringLoader(InstrumentationRegistry.getContext(), new LongStringLoadCompleteListener()
		{
			@Override
			public void onStringLoadComplete()
			{

			}
		}, null, splitLength, 0);
	}

	@Test
	public void testCreateObject() throws Exception
	{
		loader = createLoaderWithSplitLength(20);
		Assert.assertNotNull(loader);
	}
}