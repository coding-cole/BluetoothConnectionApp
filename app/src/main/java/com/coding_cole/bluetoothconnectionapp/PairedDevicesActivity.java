package com.coding_cole.bluetoothconnectionapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Set;

public class PairedDevicesActivity extends BaseActivity {

	ListView listView;
	BluetoothAdapter mBluetoothAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paired_devices);
		listView = (ListView) findViewById(R.id.paired_devices_list);
		activateToolbar(true);


		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		Set<BluetoothDevice> bt = mBluetoothAdapter.getBondedDevices();

		String[] strings = new String[bt.size()];
		int index = 0;

		if (bt.size() > 0) {
			for (BluetoothDevice device : bt) {
				strings[index] = device.getName();
				index++;
			}

			ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_list_item_1, strings);
			listView.setAdapter(arrayAdapter);

		}
	}

}
