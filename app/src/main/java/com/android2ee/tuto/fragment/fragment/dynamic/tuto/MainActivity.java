package com.android2ee.tuto.fragment.fragment.dynamic.tuto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.honeycomb.MainActivityHC;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.legacy.MainActivityLegacy;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Then do the rest
		Intent startActivityIntent = null;
		// find the version :
		boolean postHC = getResources().getBoolean(R.bool.postHC);
		// Launch the right Activity
		if (postHC) {
			Log.e("MainActivity", "MainActivityHC Launched");
			startActivityIntent = new Intent(this, MainActivityHC.class);
		} else {
			Log.e("MainActivity", "MainActivityLegacy Launched");
			startActivityIntent = new Intent(this, MainActivityLegacy.class);
		}

		startActivity(startActivityIntent);
		finish();
	}

}
