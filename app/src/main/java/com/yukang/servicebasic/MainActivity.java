package com.yukang.servicebasic;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    
    private MyService.MyBinder myBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Method to start the service
    public void startService(View view) {
        startService(new Intent(this, MyService.class));
    }

    // Method to stop the service
    public void stopService(View view) {
        Log.d("MyService", "Stop Service button clicked");
        stopService(new Intent(this, MyService.class));
    }

    // Method to bind the service
    public void bindService(View view) {
        bindService(new Intent(this, MyService.class), connection, BIND_AUTO_CREATE);
    }

    // Method to unbind service
    public void unbindService(View view) {
        Log.d("MyService", "Unbind Service button clicked");
        unbindService(connection);
    }
}
