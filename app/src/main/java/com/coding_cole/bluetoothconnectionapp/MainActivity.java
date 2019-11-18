package com.coding_cole.bluetoothconnectionapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    Button btnON, btnOFF;
    BluetoothAdapter mBluetoothAdapter;

    Intent enableBluetoothIntent;

    int REQUEST_ENABLE_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activateToolbar(false);


        btnON = findViewById(R.id.btON);
        btnOFF = findViewById(R.id.btOFF);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        REQUEST_ENABLE_CODE = 1;


        bluetoothOn();
        bluetoothOFF();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.menu_paired:

                // open a new intent and activity that has a list of paired items
                Intent pairedIntent = new Intent(this, PairedDevicesActivity.class);
                startActivity(pairedIntent);
                break;

            case R.id.menu_scan:

                /*
                 * check if bluetooth is on
                 * if turned off, turn on
                 * if turned on, open scanActivity to run scan
                 */
	            Intent scanIntent = new Intent(this, ScanActivity.class);
	            startActivity(scanIntent);
	            break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }

        return super.onOptionsItemSelected(item);
    }

    private void bluetoothOFF() {
        btnOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.disable();
                    Toast.makeText(getApplicationContext(), "Bluetooth is turned off", Toast.LENGTH_SHORT).show();
                } else if(mBluetoothAdapter.disable()) {

                    Toast.makeText(getApplicationContext(), "Bluetooth is already turned off", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void bluetoothOn() {
        btnON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBluetoothAdapter == null) {

                    Toast.makeText(getApplicationContext(), "Sorry Bluetooth is not supprted on this Device", Toast.LENGTH_SHORT).show();
                    
                } else {

                    if (mBluetoothAdapter.isEnabled()) {

                        Toast.makeText(getApplicationContext(), "Bluetooth already turned on", Toast.LENGTH_SHORT).show();
                    } else if (!mBluetoothAdapter.isEnabled()) {

                        startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_CODE);
                    }
                }
            }
        });
    }
    

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ENABLE_CODE) {
            if (resultCode == RESULT_OK) {

                Toast.makeText(getApplicationContext(), "Bluetooth is Enabled", Toast.LENGTH_SHORT).show();

            } else if (resultCode == RESULT_CANCELED) {

                Toast.makeText(getApplicationContext(), "Bluetooth enabling canceled", Toast.LENGTH_SHORT).show();

            }
        }
    }















































































}
