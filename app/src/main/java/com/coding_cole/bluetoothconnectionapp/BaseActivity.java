package com.coding_cole.bluetoothconnectionapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends AppCompatActivity {
	private static final String TAG = "BaseActivity";

	void activateToolbar(boolean enableHome) {
		Log.d(TAG, "activateToolbar: starts");
		ActionBar actionBar = getSupportActionBar();
		if  (actionBar == null) {
			Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

			if (toolbar != null) {
				setSupportActionBar(toolbar);
				actionBar = getSupportActionBar();
			}
		}

		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(enableHome);
		}
	}
}
