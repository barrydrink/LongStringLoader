package com.penrillian.longstringloaderexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void showOpenSourceLicencingUsingLsl(View view)
	{
		startActivity(new Intent(this, RecyclerViewActivityExample.class));
	}

	public void showOpenSourceLicencingWithoutUsingLsl(View view)
	{
		startActivity(new Intent(this, TextViewActivityExample.class));
	}

	public void loadStringInFragmentActivityDialogFragment(View view)
	{
		startActivity(new Intent(this, RecyclerViewFragmentActivityExample.class));
	}
}
