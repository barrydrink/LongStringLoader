package com.penrillian.longstringloaderexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.GoogleApiAvailability;
import com.penrillian.longstringloader.LongStringLoadCompleteListener;
import com.penrillian.longstringloader.LongStringLoader;
import com.penrillian.longstringloader.LongStringLoaderException;

public class MainActivity extends AppCompatActivity implements LongStringLoadCompleteListener
{
	private TextView timerTextView;
	private long startTime;
	private LongStringLoader longStringLoader;
	private Button loadStringWithLslButton;
	private Button cancelLoadStringButton;
	private RelativeLayout loadingLayout;
	private LinearLayout textViewLayout;
	private Button loadStringWithoutLslButton;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void showOpenSourceLicencingUsingLsl(View view)
	{
		if(longStringLoader != null)
		{
			longStringLoader.stop();
		}
		try
		{
			doGeneralUiSetup();

			loadingLayout = (RelativeLayout) findViewById(R.id.loading_layout);
			longStringLoader = new LongStringLoader(this, this, textViewLayout);
			cancelLoadStringButton = (Button) findViewById(R.id.cancel_load_string_button);
			cancelLoadStringButton.setEnabled(true);
			loadingLayout.setVisibility(View.VISIBLE);
			startTime = System.currentTimeMillis();
			longStringLoader.load(GoogleApiAvailability.getInstance().getOpenSourceSoftwareLicenseInfo(this));
		} catch (LongStringLoaderException ignored) {}
	}

	public void showOpenSourceLicencingWithoutUsingLsl(View view)
	{
		doGeneralUiSetup();

		timerTextView.setVisibility(View.GONE);
		final TextView textView = new TextView(this);
		textViewLayout.addView(textView);
		textView.setText(GoogleApiAvailability.getInstance().getOpenSourceSoftwareLicenseInfo(this));
		loadStringWithLslButton.setEnabled(true);
		loadStringWithoutLslButton.setEnabled(true);
	}

	public void cancelShowOpenSourceLicencing(View view)
	{
		longStringLoader.stop();
		loadingLayout.setVisibility(View.INVISIBLE);
		loadStringWithLslButton.setEnabled(true);
		cancelLoadStringButton.setEnabled(false);
	}

	@Override
	public void onStringLoadComplete()
	{
		timerTextView.setText("Time taken: " + (System.currentTimeMillis() - startTime) / 1000 + "secs");
		loadingLayout.setVisibility(View.INVISIBLE);
		loadStringWithLslButton.setEnabled(true);
		cancelLoadStringButton.setEnabled(false);
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

	private void doGeneralUiSetup()
	{
		setContentView(R.layout.activity_main);
		loadStringWithLslButton = (Button) findViewById(R.id.load_string_with_lsl_button);
		loadStringWithLslButton.setEnabled(false);
		loadStringWithoutLslButton = (Button) findViewById(R.id.load_string_without_lsl_button);
		loadStringWithoutLslButton.setEnabled(false);
		textViewLayout = (LinearLayout) findViewById(R.id.text_view_layout);
		timerTextView = (TextView) findViewById(R.id.timer_text_view);
	}
}
