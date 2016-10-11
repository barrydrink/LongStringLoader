package com.penrillian.longstringloaderexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.common.GoogleApiAvailability;

public class RecyclerViewExampleActivity extends AppCompatActivity implements LongStringLoader.LongStringLoadCompleteListener
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
		new LongStringLoader(this, GoogleApiAvailability.getInstance().getOpenSourceSoftwareLicenseInfo(this), mLongStringLayout);
	}

	@Override
	public void onLongStringLoadComplete()
	{
		mLoadingLayout.setVisibility(View.GONE);
	}
}
