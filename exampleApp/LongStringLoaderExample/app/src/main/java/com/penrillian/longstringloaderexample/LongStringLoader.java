package com.penrillian.longstringloaderexample;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.List;

public class LongStringLoader
{
	private final RecyclerView mRecyclerView;
	private final LinearLayoutManager mLinearLayoutManager;
	private final LongStringLoadCompleteListener mListener;
	private RecyclerAdapter mAdapter;

	interface LongStringLoadCompleteListener
	{
		void onLongStringLoadComplete();
	}

	public LongStringLoader(LongStringLoadCompleteListener listener, String stringToSplit, LinearLayout longStringLayout)
	{
		mListener = listener;
		mRecyclerView = new RecyclerView(longStringLayout.getContext());
		longStringLayout.addView(mRecyclerView);
		mLinearLayoutManager = new LinearLayoutManager(longStringLayout.getContext());
		mRecyclerView.setLayoutManager(mLinearLayoutManager);

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
				mAdapter = new RecyclerAdapter(strings);
				mRecyclerView.setAdapter(mAdapter);
				mListener.onLongStringLoadComplete();
			}
		}
		new Splitter().execute(stringToSplit);
	}
}
