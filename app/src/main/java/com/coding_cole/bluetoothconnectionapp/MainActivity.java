package com.coding_cole.bluetoothconnectionapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnON, btnOFF;
    BluetoothAdapter mBluetoothAdapter;

    Intent enableBluetoothIntent;

    int REQUEST_ENABLE_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnON = findViewById(R.id.btON);
        btnOFF = findViewById(R.id.btOFF);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        REQUEST_ENABLE_CODE = 1;


        bluetoothOn();
        bluetoothOFF();

    }

    private void bluetoothOFF() {
        btnOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBluetoothAdapter == null) {

                    Toast.makeText(getApplicationContext(), "Sorry Bluetooth is not supprted on this Device", Toast.LENGTH_SHORT).show();

                }
                if (mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.disable();
                    Toast.makeText(getApplicationContext(), "Bluetooth is turned off", Toast.LENGTH_SHORT).show();
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

                    if (!mBluetoothAdapter.isEnabled()) {

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
