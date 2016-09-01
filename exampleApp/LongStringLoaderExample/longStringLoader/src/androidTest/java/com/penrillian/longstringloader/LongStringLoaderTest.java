package com.penrillian.longstringloader;

import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.common.collect.Lists;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class LongStringLoaderTest
{
	private static LongStringLoader loader;

	@BeforeClass
	public static void setUp() {
		Looper.prepare();
		loader = new LongStringLoader(InstrumentationRegistry.getContext(), new LongStringLoadCompleteListener()
		{
			@Override
			public void onStringLoadComplete()
			{

			}
		}, null);
	}

	@Test
	public void testCreateObject() throws Exception
	{
		Assert.assertNotNull(loader);
	}

	@Test
	public void testGetSplitString() throws Exception
	{
		InstrumentationRegistry.getContext();
		String stringToSplit = "THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS";

		Iterable<String> splitStringIterable = loader.getSplitString(stringToSplit);
		List<String> splitStringList = Lists.newArrayList(splitStringIterable);

		Assert.assertTrue(splitStringList.size() > 0);
	}
}