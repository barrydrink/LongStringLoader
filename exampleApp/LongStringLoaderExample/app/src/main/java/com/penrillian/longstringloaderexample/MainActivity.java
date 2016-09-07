package com.penrillian.longstringloaderexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.GoogleApiAvailability;
import com.penrillian.longstringloader.LongStringLoadCompleteListener;
import com.penrillian.longstringloader.LongStringLoader;

public class MainActivity extends AppCompatActivity implements LongStringLoadCompleteListener
{
	private ProgressBar progress;
	private LinearLayout textViewLayout;
	private TextView timerTextView;
	private long startTime;
	private LongStringLoader longStringLoader;
	private Button loadStringButton;
	private Button cancelLoadStringButton;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void showOpenSourceLicencing(View view)
	{
		setContentView(R.layout.activity_main);
		cancelLoadStringButton = (Button) findViewById(R.id.cancel_load_string_button);
		cancelLoadStringButton.setEnabled(true);
		loadStringButton = (Button) findViewById(R.id.load_string_button);
		loadStringButton.setEnabled(false);
		textViewLayout = (LinearLayout) findViewById(R.id.text_view_layout);
		if(longStringLoader != null)
		{
			longStringLoader.stop();
		}
		longStringLoader = new LongStringLoader(this, this, textViewLayout);
		timerTextView = (TextView) findViewById(R.id.timer_text_view);
		progress = (ProgressBar) findViewById(R.id.loading_progress);
		progress.setVisibility(View.VISIBLE);
		startTime = System.currentTimeMillis();
		longStringLoader.load(GoogleApiAvailability.getInstance().getOpenSourceSoftwareLicenseInfo(this));
	}

	public void cancelShowOpenSourceLicencing(View view)
	{
		longStringLoader.stop();
		loadStringButton.setEnabled(true);
		cancelLoadStringButton.setEnabled(false);
		progress.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onStringLoadComplete()
	{
		progress.setVisibility(View.INVISIBLE);
		loadStringButton.setEnabled(true);
		timerTextView.setText("Time taken: " + (System.currentTimeMillis() - startTime) / 1000 + "secs");
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		if(longStringLoader != null)
		{
			longStringLoader.stop();
		}
	}
}
