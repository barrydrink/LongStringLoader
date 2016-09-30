package com.penrillian.longstringloader;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * The LongStringLoader class is the public interface that provides
 * the long string loading functionality
 */
public class LongStringLoader
{
	private int stringSplitLength = 5000;
	private int threadSleepMillis = 1000;
	private final LinearLayout containerLayout;
	private final LongStringLoadCompleteListener listener;
	private final Context context;
	private StringLoadTask stringLoadingTask;

	/**
	 * This constructor provides the default string loading behaviour.
	 * See the other constructor for more specialised behaviour
	 *
	 * @param context						context used to create TextViews. Not null.
	 * @param listener						the listener to be notified when the long
	 *                          			string has been completely loaded.
	 * @param containerLayout				the layout where the long string is to be displayed.
	 *                          			TextView objects will be appended to this. Not null.
	 * @throws LongStringLoaderException	if parameters are invalid.
	 */
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

	/**
	 * This constructor provides more control over the string loading process.
	 * The <code>splitStringLength</code> and <code>threadSleepMillis</code>
	 * params will alter the responsiveness of the UI and speed of string loading
	 *
	 * @param context						context used to create TextViews. Not null.
	 * @param listener						the listener to be notified when the long
	 *                          			string has been completely loaded.
	 * @param containerLayout				the layout where the long string is to be displayed.
	 *                          			TextView objects will be appended to this. Not null.
	 * @param splitStringLength				the length the long string will be broken up into. These
	 *                          			'chunks' of the long string are loaded one by one.
	 * @param threadSleepMillis				the length of time in milliseconds the LongStringLoader will sleep between loading
	 *                          			each chunk of string. Must be above -1
	 * @throws LongStringLoaderException	if parameters are invalid.
	 */
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

	/**
	 * This constructor provides more control over the string loading process.
	 * The <code>splitStringLength</code> and <code>threadSleepMillis</code>
	 * params will alter the responsiveness of the UI and speed of string loading
	 *
	 * @param stringToLoad		the string to display
	 */
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

	/**
	 * Stop the string loading process
	 */
	public void stop()
	{
		stringLoadingTask.stop();
	}
}
