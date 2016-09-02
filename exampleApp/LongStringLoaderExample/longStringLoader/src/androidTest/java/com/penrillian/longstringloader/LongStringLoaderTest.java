package com.penrillian.longstringloader;

import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

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

	@Test
	public void testGetLengthOfLongestWord() throws Exception
	{
		loader = createLoaderWithSplitLength(20);
		Assert.assertEquals(9, loader.getLengthOfLongestWord("THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS"));
	}

	@Test
	public void testGetSplitString() throws Exception
	{
		String stringToSplit = "THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS";

		loader = createLoaderWithSplitLength(20);
		List<String> splitStringList = loader.getSplitString(stringToSplit);

		Assert.assertTrue(splitStringList.size() > 0);
		Assert.assertEquals("THIS SOFTWARE IS", splitStringList.get(0));
		Assert.assertEquals("PROVIDED BY THE", splitStringList.get(1));
		Assert.assertEquals("COPYRIGHT HOLDERS", splitStringList.get(2));

		loader = createLoaderWithSplitLength(12);
		splitStringList = loader.getSplitString(stringToSplit);

		Assert.assertTrue(splitStringList.size() > 0);
		Assert.assertEquals("THIS", splitStringList.get(0));
		Assert.assertEquals("SOFTWARE IS", splitStringList.get(1));
		Assert.assertEquals("PROVIDED BY", splitStringList.get(2));
		Assert.assertEquals("THE", splitStringList.get(3));
		Assert.assertEquals("COPYRIGHT", splitStringList.get(4));
		Assert.assertEquals("HOLDERS", splitStringList.get(5));

		loader = createLoaderWithSplitLength(1);
		splitStringList = loader.getSplitString(stringToSplit);

		Assert.assertTrue(splitStringList.size() > 0);
		Assert.assertEquals("THIS", splitStringList.get(0));
		Assert.assertEquals("SOFTWARE", splitStringList.get(1));
		Assert.assertEquals("IS", splitStringList.get(2));
		Assert.assertEquals("PROVIDED", splitStringList.get(3));
		Assert.assertEquals("BY THE", splitStringList.get(4));
		Assert.assertEquals("COPYRIGHT", splitStringList.get(5));
		Assert.assertEquals("HOLDERS", splitStringList.get(6));

		loader = createLoaderWithSplitLength(10);
		splitStringList = loader.getSplitString("1234567890");

		Assert.assertTrue(splitStringList.size() == 1);
		Assert.assertEquals("1234567890", splitStringList.get(0));

		loader = createLoaderWithSplitLength(10);
		splitStringList = loader.getSplitString("12345678901");

		Assert.assertTrue(splitStringList.size() == 1);
		Assert.assertEquals("12345678901", splitStringList.get(0));

		loader = createLoaderWithSplitLength(11);
		splitStringList = loader.getSplitString("1234567890");

		Assert.assertTrue(splitStringList.size() == 1);
		Assert.assertEquals("1234567890", splitStringList.get(0));
	}
}