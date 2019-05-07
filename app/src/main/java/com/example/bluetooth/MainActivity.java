package com.example.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
     Button b1,b2;
     BluetoothAdapter obj;
     Intent intent;
     int request;
     ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        lv = findViewById(R.id.list);
        obj = BluetoothAdapter.getDefaultAdapter();
        intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        request=1;
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        Set<BluetoothDevice> bt = obj.getBondedDevices();
        int x = bt.size();
        String[] str = new String[x];
        int index=0;

        if
        (bt.size() > 0)
        {
            for(BluetoothDevice device :bt)
            {
                str[index]=device.getName();
                index++ ;
            }

        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,str);
        lv.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == request)
        {
            if(resultCode == RESULT_OK)
                Toast.makeText(this," bluetooth enabled",Toast.LENGTH_LONG).show();
            else
            {
                if(resultCode == RESULT_CANCELED)
                    Toast.makeText(this," bluetooth not enabled",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.button)
        {
            if(obj == null)
            {
                Toast.makeText(this,"does not support bluetooth",Toast.LENGTH_LONG).show();
            }
            else
            {
                if(!obj.isEnabled())
                {
                startActivityForResult(intent,request);
                }
            }
        }
        else
        {
            if(v.getId() == R.id.button2)
            {
                if(obj.isEnabled())
                {
                    obj.disable();
                    Toast.makeText(this," bluetooth disabled",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
