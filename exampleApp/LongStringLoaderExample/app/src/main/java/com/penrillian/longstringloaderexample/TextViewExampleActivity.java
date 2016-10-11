package com.penrillian.longstringloaderexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.common.GoogleApiAvailability;

public class TextViewExampleActivity extends AppCompatActivity
{
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_view_example);

		mTextView = (TextView) findViewById(R.id.licence_text_view);
		mTextView.setText(GoogleApiAvailability.getInstance().getOpenSourceSoftwareLicenseInfo(this));
	}
}
