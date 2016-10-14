package com.penrillian.longstringloaderexample;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.GoogleApiAvailability;

public class RecyclerViewDialogFragmentExample1 extends DialogFragment
{
	private Button showLicenceButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.recycler_view_dialog_fragment_example_1, container);
		getDialog().setTitle("About");

		final String licence = GoogleApiAvailability.getInstance().getOpenSourceSoftwareLicenseInfo(getContext());
		if(licence != null && licence.length() > 0)
		{
			showLicenceButton = (Button) view.findViewById(R.id.show_open_source_licences);
			showLicenceButton.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					Bundle bundle = new Bundle();
					bundle.putString("licence_text", licence);
					RecyclerViewDialogFragmentExample2 licenceDialog = new RecyclerViewDialogFragmentExample2();
					licenceDialog.setArguments(bundle);
					FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction().remove(RecyclerViewDialogFragmentExample1.this);
					licenceDialog.show(transaction, "");
				}
			});
		}

		return view;

	}
}
