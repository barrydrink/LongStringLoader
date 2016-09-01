package com.penrillian.longstringloader;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.common.base.Splitter;


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
			Iterable<String> licenceList =  Splitter.fixedLength(stringSplitLength).split(stringToLoad);
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
						 listener.onLicenceLoadCompolete();
					 }
				 }
			);
		}
	}
}
