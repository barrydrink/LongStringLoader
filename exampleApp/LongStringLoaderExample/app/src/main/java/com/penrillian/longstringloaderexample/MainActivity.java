package com.penrillian.longstringloaderexample;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.gms.common.GoogleApiAvailability;

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

	public void loadStringInWebViewInDialog(View view)
	{
		//http://stackoverflow.com/a/40811303/201113
		String licenseInfo =  GoogleApiAvailability.getInstance().getOpenSourceSoftwareLicenseInfo(this);
		licenseInfo = licenseInfo.replace("\n", "<br/>");

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Legal notices");

		WebView webView = new WebView(this);
		webView.loadData("<html><body>"+licenseInfo+"</body></html>", "text/html", "utf-8");
		WebSettings webSettings = webView.getSettings();
		webSettings.setDefaultFontSize(12);

		builder.setView(webView);
		builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		final AlertDialog createDialog = builder.create();
		createDialog.show();
	}
}
