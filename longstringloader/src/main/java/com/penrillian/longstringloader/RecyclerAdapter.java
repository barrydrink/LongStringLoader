package com.penrillian.longstringloader;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.StringHolder>
{

	private List<String> mStrings;

	public static class StringHolder extends RecyclerView.ViewHolder
	{
		private TextView mItem;

		public StringHolder(View v)
		{
			super(v);
			mItem = (TextView) v;
		}

		public void bindString(String string)
		{
			mItem.setText(string);
		}
	}

	public RecyclerAdapter(List<String> strings)
	{
		mStrings = strings;
	}

	@Override
	public RecyclerAdapter.StringHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		return new StringHolder(new TextView(parent.getContext()));
	}

	@Override
	public void onBindViewHolder(StringHolder holder, int position)
	{
		String itemString = mStrings.get(position);
		holder.bindString(itemString);
	}

	@Override
	public int getItemCount()
	{
		return mStrings.size();
	}
}
