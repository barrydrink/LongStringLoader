package com.penrillian.longstringloader;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class LongStringLoader
{
	private int stringSplitLength = 5000;
	private int threadSleepMillis = 1000;
	private final LinearLayout containerLayout;
	private final LongStringLoadCompleteListener listener;
	private final Context context;
	private StringLoadTask stringLoadingTask;

	public LongStringLoader(@NonNull Context context, LongStringLoadCompleteListener listener, @NonNull LinearLayout containerLayout) throws LongStringLoaderException
	{
		if(context == null || containerLayout == null || threadSleepMillis < 0)
		{
			throw new LongStringLoaderException("Invalid parameters");
		}
		this.context = context;
		this.listener = listener;
		this.containerLayout = containerLayout;
	}

	public LongStringLoader(@NonNull Context context, LongStringLoadCompleteListener listener, @NonNull LinearLayout containerLayout, int splitStringLength, int threadSleepMillis) throws LongStringLoaderException
	{
		this(context, listener, containerLayout);
		if(threadSleepMillis < 0)
		{
			throw new LongStringLoaderException("Invalid parameters");
		}
		this.threadSleepMillis = threadSleepMillis;
		this.stringSplitLength = splitStringLength;
	}

	public void load(String stringToLoad)
	{
		stringLoadingTask = new StringLoadTask(stringToLoad);
		new Thread(stringLoadingTask).start();
	}
	Handler handler = new Handler();

	class StringLoadTask implements Runnable
	{
		private volatile boolean stop;
		private final String stringToLoad;
		TextView currentTextView;

		public StringLoadTask(String stringToLoad)
		{
			this.stringToLoad = stringToLoad;
		}

		public void stop()
		{
			stop = true;
		}

		@Override
		public void run()
		{
			stop = false;
			List<String> licenceList = LongStringUtils.getSplitString(stringToLoad, stringSplitLength);
			for (final String string : licenceList)
			{
				if(stop)
					return;
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

	public void stop()
	{
		stringLoadingTask.stop();
	}
}
