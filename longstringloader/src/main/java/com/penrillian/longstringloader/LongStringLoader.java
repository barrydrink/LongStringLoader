package com.penrillian.longstringloader;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.List;

/**
 * The LongStringLoader class is the public interface that provides
 * the long string loading functionality
 */
public class LongStringLoader
{
	private RecyclerView mRecyclerView;
	private LinearLayoutManager mLinearLayoutManager;
	private final LongStringLoadCompleteListener mListener;
	private final LinearLayout mContainerLayout;
	private RecyclerAdapter mAdapter;

	/**
	 * This constructor set up the object and loads the string into the UI
	 *
	 * @param listener						the listener to be notified when the long
	 *                          			string has been processed and asynchronous
	 *                          			displaying has begun. The UI should be fully responsive at this point.
	 * @param stringToLoad					the string to display
	 * @param containerLayout				the layout where the long string is to be displayed.
	 *                          			TextView objects will be appended to this. Not null.
	 * @throws LongStringLoaderException	if parameters are invalid.
	 */
	public LongStringLoader(LongStringLoadCompleteListener listener, String stringToLoad, LinearLayout containerLayout) throws LongStringLoaderException
	{
		if(containerLayout == null)
		{
			throw new LongStringLoaderException("Invalid parameters");
		}
		mListener = listener;
		mContainerLayout = containerLayout;

		class Splitter extends AsyncTask<String, Void, List<String>>
		{
			@Override
			protected List<String> doInBackground(String... params)
			{
				return LongStringUtils.getSplitString(params[0], 5000);
			}

			@Override
			protected void onPostExecute(List<String> strings)
			{
				super.onPostExecute(strings);

				mRecyclerView = new RecyclerView(mContainerLayout.getContext());
				mContainerLayout.addView(mRecyclerView);
				mLinearLayoutManager = new LinearLayoutManager(mContainerLayout.getContext());
				mRecyclerView.setLayoutManager(mLinearLayoutManager);

				mAdapter = new RecyclerAdapter(strings);
				mRecyclerView.setAdapter(mAdapter);
				if(mListener != null)
				{
					mListener.onLongStringLoadComplete();
				}
			}
		}
		new Splitter().execute(stringToLoad);
	}
}
