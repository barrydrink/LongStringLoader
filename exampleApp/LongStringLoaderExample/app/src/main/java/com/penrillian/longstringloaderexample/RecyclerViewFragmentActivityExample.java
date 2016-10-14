package com.penrillian.longstringloaderexample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class RecyclerViewFragmentActivityExample extends FragmentActivity
{

	private android.support.v4.app.FragmentTransaction transaction;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recycler_view_fragment_activity_example);
		transaction = getSupportFragmentManager().beginTransaction();
		RecyclerViewDialogFragmentExample1 aboutDialog = new RecyclerViewDialogFragmentExample1();
		aboutDialog.show(transaction, "");
	}
}
