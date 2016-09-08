package com.penrillian.longstringloader;

import android.content.Context;
import android.os.Looper;
import android.widget.LinearLayout;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LongStringLoaderTest
{
	private static LongStringLoadCompleteListener longStringLoadCompleteListener;
	private static LinearLayout linearLayout;
	@Mock
	private static Context context;

	@BeforeClass
	public static void setUp() {
		Looper.prepare();

		linearLayout = new LinearLayout(context);
		longStringLoadCompleteListener = new LongStringLoadCompleteListener()
		{
			@Override
			public void onStringLoadComplete()
			{

			}
		};
	}

	private LongStringLoader createLoaderWithSplitLength(int splitLength) throws LongStringLoaderException
	{
		return new LongStringLoader(context, longStringLoadCompleteListener, linearLayout, splitLength, 0);
	}

	@Test
	public void testCreateObject() throws LongStringLoaderException
	{
		LongStringLoader loader = createLoaderWithSplitLength(20);
		Assert.assertNotNull(loader);
	}

	@Test
	public void testCreateWithInvalidParamsThrowsException1()
	{
		LongStringLoader loader = null;
		try
		{
			loader = new LongStringLoader(null, longStringLoadCompleteListener, linearLayout);
		} catch (LongStringLoaderException e)
		{
			Assert.assertNull(loader);
			return;
		}
		Assert.assertTrue(false);
	}

	@Test
	public void testCreateWithInvalidParamsThrowsException2()
	{
		LongStringLoader loader = null;
		try
		{
			loader = new LongStringLoader(context, longStringLoadCompleteListener, null);
		} catch (LongStringLoaderException e)
		{
			Assert.assertNull(loader);
			return;
		}
		Assert.assertTrue(false);
	}

	@Test
	public void testCreateWithInvalidParamsThrowsException3()
	{
		LongStringLoader loader = null;
		try
		{
			loader = new LongStringLoader(context, longStringLoadCompleteListener, linearLayout, 100, -55);
		} catch (LongStringLoaderException e)
		{
			Assert.assertNull(loader);
			return;
		}
		Assert.assertTrue(false);
	}
}