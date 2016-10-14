package com.penrillian.longstringloaderexample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.penrillian.longstringloader.LongStringLoadCompleteListener;
import com.penrillian.longstringloader.LongStringLoader;
import com.penrillian.longstringloader.LongStringLoaderException;

public class OpenSourceLicenceDialogFragment extends DialogFragment implements LongStringLoadCompleteListener
{
	private String licence;
	private LinearLayout mLongStringLayout;
	private View view;
	private RelativeLayout mLoadingLayout;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		licence = getArguments().getString("licence_text");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		view = inflater.inflate(R.layout.open_source_licencing, container);
		getDialog().setTitle("Open Source Licences");

		mLoadingLayout = (RelativeLayout) view.findViewById(R.id.loading_layout);
		mLongStringLayout = (LinearLayout) view.findViewById(R.id.open_source_info_layout);
		try
		{
			new LongStringLoader(this, mLongStringLayout).load(licence);
		} catch (LongStringLoaderException e)
		{
			e.printStackTrace();
		}
		return view;
	}

	@Override
	public void onLongStringLoadComplete()
	{
		mLoadingLayout.setVisibility(View.GONE);
	}

	@Override
	public void onDismiss(DialogInterface dialog)
	{
		super.onDismiss(dialog);
		getActivity().finish();
	}
}
