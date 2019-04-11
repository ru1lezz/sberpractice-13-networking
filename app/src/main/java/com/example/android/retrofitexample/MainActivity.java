package com.example.android.retrofitexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private boolean mIsBound;
    private RecyclerView mRecyclerView;
    private WeatherAdapter mAdapter;
    private WeatherService mService;
    private Handler mHandler;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = ((WeatherService.WeatherBinder) service).getService();
            mService.downloadDailyWeather(weatherList -> mHandler.post(() -> mAdapter.setWeatherList(weatherList)));
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        initRecycler();
        mHandler = new Handler(Looper.getMainLooper());
    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mAdapter = new WeatherAdapter(MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bindService(WeatherService.newIntent(MainActivity.this), mServiceConnection, Context.BIND_AUTO_CREATE)) {
            mIsBound = true;
        } else {

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mIsBound) {
            unbindService(mServiceConnection);
            mIsBound = false;
        }
    }
}
