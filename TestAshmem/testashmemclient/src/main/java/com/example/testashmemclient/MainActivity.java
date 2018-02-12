package com.example.testashmemclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sharedmemlib.ISharedMem;

public class MainActivity extends AppCompatActivity implements ServiceConnection{

    ISharedMem ShmMemService;
    Button b;
    EditText ed,ed2;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService(new Intent("com.example.developer.testashmem.ShmService").setPackage("com.example.developer.testashmem"),this,BIND_AUTO_CREATE);
        tv = (TextView)findViewById(R.id.tv);
        ed=(EditText)findViewById(R.id.ed);
        ed2 = (EditText)findViewById(R.id.ed2);
        b=(Button)findViewById(R.id.btnGet);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    tv.setText("val:" + ShmClientLib.getVal(Integer.parseInt(ed2.getText().toString())));

             }
        });
        Button bset = (Button)findViewById(R.id.btnSet);
        bset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShmClientLib.setVal(Integer.parseInt(ed2.getText().toString()),Integer.parseInt(ed.getText().toString()));
            }
        });
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ShmMemService = ISharedMem.Stub.asInterface(iBinder);
        try {
            ParcelFileDescriptor p = ShmMemService.OpenSharedMem("sh1", 1000, false);
            ShmClientLib.setMap(p.getFd(), 1000);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }
}
