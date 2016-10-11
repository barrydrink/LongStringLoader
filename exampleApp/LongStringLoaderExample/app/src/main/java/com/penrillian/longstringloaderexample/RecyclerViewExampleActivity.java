package com.penrillian.longstringloaderexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.common.GoogleApiAvailability;
import com.penrillian.longstringloader.LongStringLoadCompleteListener;
import com.penrillian.longstringloader.LongStringLoader;
import com.penrillian.longstringloader.LongStringLoaderException;

public class RecyclerViewExampleActivity extends AppCompatActivity implements LongStringLoadCompleteListener
{
	private LinearLayout mLongStringLayout;
	private RelativeLayout mLoadingLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recycler_view_example);

		mLoadingLayout = (RelativeLayout) findViewById(R.id.loading_layout);
		mLongStringLayout = (LinearLayout) findViewById(R.id.long_string_layout);
		try
		{
			new LongStringLoader(this, GoogleApiAvailability.getInstance().getOpenSourceSoftwareLicenseInfo(this), mLongStringLayout);
		} catch (LongStringLoaderException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onLongStringLoadComplete()
	{
		mLoadingLayout.setVisibility(View.GONE);
	}
}
