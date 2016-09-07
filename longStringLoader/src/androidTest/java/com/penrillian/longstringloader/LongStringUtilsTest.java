package com.penrillian.longstringloader;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class LongStringUtilsTest
{
	@Test
	public void testGetLengthOfLongestWord() throws Exception
	{
		Assert.assertEquals(9, LongStringUtils.getLengthOfLongestWord("THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS"));
	}

	@Test
	public void testGetLengthOfLongestWordWhenSeriesOfNonWordCharactersExist() throws Exception
	{
		Assert.assertEquals(74, LongStringUtils.getLengthOfLongestWord("==========================================================================\n" +
				"\n" +
				"This Open Source Licenses:"));
	}

	@Test
	public void testGetSplitString() throws Exception
	{
		String stringToSplit = "THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS";

		List<String> splitStringList = LongStringUtils.getSplitString(stringToSplit, 20);

		Assert.assertTrue(splitStringList.size() > 0);
		Assert.assertEquals("THIS SOFTWARE IS", splitStringList.get(0));
		Assert.assertEquals("PROVIDED BY THE", splitStringList.get(1));
		Assert.assertEquals("COPYRIGHT HOLDERS", splitStringList.get(2));

		splitStringList = LongStringUtils.getSplitString(stringToSplit, 12);

		Assert.assertTrue(splitStringList.size() > 0);
		Assert.assertEquals("THIS", splitStringList.get(0));
		Assert.assertEquals("SOFTWARE IS", splitStringList.get(1));
		Assert.assertEquals("PROVIDED BY", splitStringList.get(2));
		Assert.assertEquals("THE", splitStringList.get(3));
		Assert.assertEquals("COPYRIGHT", splitStringList.get(4));
		Assert.assertEquals("HOLDERS", splitStringList.get(5));

		splitStringList = LongStringUtils.getSplitString(stringToSplit, 1);

		Assert.assertTrue(splitStringList.size() > 0);
		Assert.assertEquals("THIS", splitStringList.get(0));
		Assert.assertEquals("SOFTWARE", splitStringList.get(1));
		Assert.assertEquals("IS", splitStringList.get(2));
		Assert.assertEquals("PROVIDED", splitStringList.get(3));
		Assert.assertEquals("BY THE", splitStringList.get(4));
		Assert.assertEquals("COPYRIGHT", splitStringList.get(5));
		Assert.assertEquals("HOLDERS", splitStringList.get(6));

		splitStringList = LongStringUtils.getSplitString("1234567890", 10);

		Assert.assertTrue(splitStringList.size() == 1);
		Assert.assertEquals("1234567890", splitStringList.get(0));

		splitStringList = LongStringUtils.getSplitString("12345678901", 10);

		Assert.assertTrue(splitStringList.size() == 1);
		Assert.assertEquals("12345678901", splitStringList.get(0));

		splitStringList = LongStringUtils.getSplitString("1234567890", 11);

		Assert.assertTrue(splitStringList.size() == 1);
		Assert.assertEquals("1234567890", splitStringList.get(0));
	}

	@Test
	public void testGetSplitStringWithLineBreakAtSplitPoint() throws Exception
	{
		String stringToSplit = "==========================================================================\n" +
				"\n" +
				"\n" +
				"Open Source Licenses:\n" +
				"\n" +
				"==========================================================================\n" +
				"\n" +
				"This is a string with lots of white space and non alpha-numeric characters";
		List<String> splitStringList = LongStringUtils.getSplitString(stringToSplit, 9);

		Assert.assertTrue(splitStringList.size() > 0);
		Assert.assertEquals("==========================================================================", splitStringList.get(0));
		Assert.assertEquals("Open Source Licenses:\n", splitStringList.get(1));
		Assert.assertEquals("==========================================================================", splitStringList.get(2));
		Assert.assertEquals("This is a string with lots of white space and non alpha-numeric characters", splitStringList.get(3));
	}
}