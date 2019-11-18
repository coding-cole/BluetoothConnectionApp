package com.coding_cole.bluetoothconnectionapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ScanActivity extends BaseActivity {

	ListView scanListView;
	ArrayList<String> stringArrayList = new ArrayList<String>();
	ArrayAdapter<String> arrayAdapter;
	BluetoothAdapter mAdapter;
	FloatingActionButton fab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
		scanListView = (ListView) findViewById(R.id.scan_list);

		activateToolbar(true);

		fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mAdapter.isDiscovering()) {
					mAdapter.cancelDiscovery();

					mAdapter.startDiscovery();
					IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
					registerReceiver(mReciver, intentFilter);
					arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
					scanListView.setAdapter(arrayAdapter);
				}
			}
		});


	}

	private final BroadcastReceiver mReciver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

				stringArrayList.add(device.getName());
				arrayAdapter.notifyDataSetChanged();
			}

		}
	};

	private final BroadcastReceiver receiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				// Discovery has found a device. Get the BluetoothDevice
				// object and its info from the Intent.
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				String deviceName = device.getName();
				String deviceHardwareAddress = device.getAddress(); // MAC address
			}
		}
	};

}