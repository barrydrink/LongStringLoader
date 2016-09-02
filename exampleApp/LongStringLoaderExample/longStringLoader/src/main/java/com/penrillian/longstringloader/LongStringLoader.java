package com.penrillian.longstringloader;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LongStringLoader
{
	private int stringSplitLength = 5000;
	private int threadSleepMillis = 1000;
	private final LinearLayout containerLayout;
	private final LongStringLoadCompleteListener listener;
	private final Context context;

	public LongStringLoader(Context context, LongStringLoadCompleteListener listener, LinearLayout containerLayout)
	{
		this.context = context;
		this.listener = listener;
		this.containerLayout = containerLayout;
	}

	public LongStringLoader(Context context, LongStringLoadCompleteListener listener, LinearLayout containerLayout, int splitStringLength, int threadSleepMillis)
	{
		this(context, listener, containerLayout);
		this.threadSleepMillis = threadSleepMillis;
		this.stringSplitLength = splitStringLength;
	}

	public void load(String stringToLoad)
	{
		new Thread(new Task(stringToLoad)).start();
	}
	Handler handler = new Handler();

	class Task implements Runnable
	{
		private final String stringToLoad;
		TextView currentTextView;

		public Task(String stringToLoad)
		{
			this.stringToLoad = stringToLoad;
		}

		@Override
		public void run()
		{
			List<String> licenceList = getSplitString(stringToLoad);
			for (final String string : licenceList)
			{
				handler.post(new Runnable()
				{
					@Override
					public void run()
					{
					currentTextView = new TextView(context);
					currentTextView.setVisibility(View.GONE);
					currentTextView.append(string);
					currentTextView.setVisibility(View.VISIBLE);
					containerLayout.addView(currentTextView);
					}
				});
				try
				{
					Thread.sleep(threadSleepMillis);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			handler.post(new Runnable()
				 {
					 @Override
					 public void run()
					 {
						 listener.onStringLoadComplete();
					 }
				 }
			);
		}

	}

	@NonNull
	public List<String> getSplitString(String stringToSplit)
	{
		return fixBrokenWords(Lists.newArrayList(Splitter.fixedLength(stringSplitLength).split(stringToSplit)));
	}

	@NonNull
	public List<String> fixBrokenWords(List<String> splitStringList)
	{
		String startOfBrokenString;
		String endOfBrokenString;
		Matcher endStringMatcher;
		Matcher startStringMatcher;
		Matcher startWhiteSpaceStringMatcher;
		for(int i = 0; i < splitStringList.size() - 1; i++)
		{
			endStringMatcher = Pattern.compile("\\w$", Pattern.CASE_INSENSITIVE).matcher(splitStringList.get(i));
			startStringMatcher = Pattern.compile("^\\w", Pattern.CASE_INSENSITIVE).matcher(splitStringList.get(i+1));
			if(endStringMatcher.find() && startStringMatcher.find())
			{
				startOfBrokenString = getStartOfBrokenString(splitStringList.get(i));
				endOfBrokenString = getEndOfBrokenString(splitStringList.get(i+1));
				splitStringList.add(i, splitStringList.get(i).replace(startOfBrokenString, startOfBrokenString + endOfBrokenString));
				splitStringList.remove(i+1);

				String s = splitStringList.get(i+1).replace(endOfBrokenString, "");
				startWhiteSpaceStringMatcher = Pattern.compile("^\\s").matcher(s);
				if(startWhiteSpaceStringMatcher.find())
				{
					s = startWhiteSpaceStringMatcher.replaceFirst("");

				}
				splitStringList.add(i+1, s);
				splitStringList.remove(i+2);
			}
		}
		return splitStringList;
	}

	@NonNull
	public String getEndOfBrokenString(String s)
	{
		Matcher m = Pattern.compile("\\s", Pattern.CASE_INSENSITIVE).matcher(s);
		if(m.find())
		{
			return s.substring(0, m.start()).trim();
		}
		return "";
	}

	public String getStartOfBrokenString(String s)
	{
		Matcher m = Pattern.compile("\\s\\w+$", Pattern.CASE_INSENSITIVE).matcher(s);
		if(m.find())
		{
			return s.substring(m.start()).trim();
		}
		return "";
	}
}
