package com.penrillian.longstringloaderexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.gms.common.GoogleApiAvailability;
import com.penrillian.longstringloader.LongStringLoadCompleteListener;
import com.penrillian.longstringloader.LongStringLoader;

public class MainActivity extends AppCompatActivity implements LongStringLoadCompleteListener
{
	private ProgressBar progress;
	private LinearLayout textViewLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progress = (ProgressBar) findViewById(R.id.loading_progress);
		textViewLayout = (LinearLayout) findViewById(R.id.text_view_layout);
	}

	public void showOpenSourceLicencing(View view)
	{
		progress.setVisibility(View.VISIBLE);
		new LongStringLoader(this, this, textViewLayout).load(GoogleApiAvailability.getInstance().getOpenSourceSoftwareLicenseInfo(this));
	}

	@Override
	public void onLicenceLoadComplete()
	{
		progress.setVisibility(View.INVISIBLE);
	}
}
