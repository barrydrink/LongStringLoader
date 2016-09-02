package com.penrillian.longstringloader;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
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
		stringSplitLength = Math.max(getLengthOfLongestWord(stringToSplit), stringSplitLength);

		List<String> splitString = new ArrayList<>();

		Pattern p = Pattern.compile("\\G\\s*(.{1,"+stringSplitLength+"})(?=\\s|$)", Pattern.DOTALL);
		Matcher m2 = p.matcher(stringToSplit);
		while (m2.find())
		{
			splitString.add(m2.group(1));
		}
		return splitString;
	}

	public int getLengthOfLongestWord(String stringToSplit)
	{
		int longestWordLength = 0;
		Matcher m = Pattern.compile("\\s*(\\w+)", Pattern.CASE_INSENSITIVE).matcher(stringToSplit);
		while(m.find())
		{
			longestWordLength = Math.max(longestWordLength, m.group(1).length());
		}
		return  longestWordLength;
	}
}
