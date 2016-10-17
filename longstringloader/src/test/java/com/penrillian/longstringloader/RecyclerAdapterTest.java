package com.penrillian.longstringloader;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

public class RecyclerAdapterTest
{
	@Test
	public void testGetItemCount() throws Exception
	{
		ArrayList<String> strings = new ArrayList<>();
		strings.add("1");
		strings.add("2");
		strings.add("3");
		RecyclerAdapter adapter = new RecyclerAdapter(strings);
		Assert.assertEquals(3, adapter.getItemCount());
	}

}