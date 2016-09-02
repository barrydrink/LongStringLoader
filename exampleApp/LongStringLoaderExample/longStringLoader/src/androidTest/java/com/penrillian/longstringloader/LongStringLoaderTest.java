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
	private static LongStringLoader loader;

	@BeforeClass
	public static void setUp() {
		Looper.prepare();
		loader = createLoaderWithSplitLength(20);
	}

	private static LongStringLoader createLoaderWithSplitLength(int splitLength)
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
		Assert.assertNotNull(loader);
	}

	@Test
	public void testGetSplitString() throws Exception
	{
		String stringToSplit = "THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS";

		List<String> splitStringList = loader.getSplitString(stringToSplit);

		Assert.assertTrue(splitStringList.size() > 0);
		Assert.assertEquals("THIS SOFTWARE IS PROVIDED", splitStringList.get(0));

		loader = createLoaderWithSplitLength(12);
		splitStringList = loader.getSplitString(stringToSplit);

		Assert.assertTrue(splitStringList.size() > 0);
		Assert.assertEquals("THIS SOFTWARE", splitStringList.get(0));
		Assert.assertEquals("IS PROVIDED", splitStringList.get(1));

		loader = createLoaderWithSplitLength(1);
		splitStringList = loader.getSplitString(stringToSplit);

		Assert.assertTrue(splitStringList.size() > 0);
		Assert.assertEquals("THIS", splitStringList.get(0));
		Assert.assertEquals("SOFTWARE", splitStringList.get(1));

	}

	@Test
	public void testFixBrokenWords() throws Exception
	{
		List<String> splitStringListWithBrokenWords = new ArrayList<>();
		splitStringListWithBrokenWords.add("THIS SOFTWARE IS PROV");
		splitStringListWithBrokenWords.add("IDED BY THE COPYRIGHT HOLDERS");

		List<String> splitStringListWithFixedWords = loader.fixBrokenWords(splitStringListWithBrokenWords);

		Assert.assertEquals("THIS SOFTWARE IS PROVIDED", splitStringListWithFixedWords.get(0));
		Assert.assertEquals("BY THE COPYRIGHT HOLDERS", splitStringListWithFixedWords.get(1));
	}

	@Test
	public void testGetStartOfBrokenString() throws Exception
	{
		String stringWithBrokenWord = "THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLD";
		Assert.assertEquals("HOLD", loader.getStartOfBrokenString(stringWithBrokenWord));
	}

	@Test
	public void testGetEndOfBrokenString() throws Exception
	{
		String stringWithBrokenWord = "TWARE IS PROVIDED BY THE COPYRIGHT HOLDERS";
		Assert.assertEquals("TWARE", loader.getEndOfBrokenString(stringWithBrokenWord));
	}
}