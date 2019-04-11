package com.example.android.retrofitexample;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.retrofitexample.model.Weather;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherActivity extends AppCompatActivity {

    public static final String EXTRA_EPOCH = "extra_epoch";
    private WeatherApplication mWeatherApplication;
    private ImageView mIcon;
    private TextView highTempTextView, lowTempTextView, conditionTextView;
    private long mWeatherEpoch;
    private ExecutorService mExecutorService;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initViews();
        mWeatherApplication = (WeatherApplication) getApplication();
        mWeatherEpoch = getIntent().getExtras().getLong(EXTRA_EPOCH);
        mExecutorService = Executors.newSingleThreadExecutor();
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mExecutorService.execute(() -> {
            Weather weather = mWeatherApplication.getLocalRepository().getWeatherByEpoch(mWeatherEpoch);
            mHandler.post(() -> {
                Picasso.get()
                        .load("http:" + weather.getDay().getCondition().getIconUrl())
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(mIcon);
                highTempTextView.setText(String.valueOf(weather.getDay().getMaxTemp()));
                lowTempTextView.setText(String.valueOf(weather.getDay().getMinTemp()));
                conditionTextView.setText(weather.getDay().getCondition().getText());
            });
        });
    }

    private void initViews() {
        highTempTextView = findViewById(R.id.high_temperature_text_view);
        lowTempTextView = findViewById(R.id.low_temperature_text_view);
        conditionTextView = findViewById(R.id.condition_text_view);
        mIcon = findViewById(R.id.icon_image_view);
    }

    public static Intent newIntent(Context context, long epoch) {
        Intent intent = new Intent(context, WeatherActivity.class);
        intent.putExtra(EXTRA_EPOCH, epoch);
        return intent;
    }
}
