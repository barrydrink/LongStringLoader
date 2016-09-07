package com.penrillian.longstringloader;

import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.LinearLayout;

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

	private LongStringLoader createLoaderWithSplitLength(int splitLength) throws LongStringLoaderException
	{
		return new LongStringLoader(InstrumentationRegistry.getContext(), new LongStringLoadCompleteListener()
		{
			@Override
			public void onStringLoadComplete()
			{

			}
		}, new LinearLayout(InstrumentationRegistry.getContext()), splitLength, 0);
	}

	@Test
	public void testCreateObject() throws Exception, LongStringLoaderException
	{
		LongStringLoader loader = createLoaderWithSplitLength(20);
		Assert.assertNotNull(loader);
	}
}