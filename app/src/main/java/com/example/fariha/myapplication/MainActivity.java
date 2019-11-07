package com.example.fariha.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;

import io.particle.android.sdk.cloud.ParticleCloud;
import io.particle.android.sdk.cloud.ParticleCloudSDK;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.cloud.ParticleEvent;
import io.particle.android.sdk.cloud.ParticleEventHandler;
import io.particle.android.sdk.cloud.exceptions.ParticleCloudException;
import io.particle.android.sdk.utils.Async;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Manpreet";
    public int counter;
    TextView timer;
    Button button;

    private final String PARTICLE_USERNAME = "manubhatti2309@gmail.com";
    private final String PARTICLE_PASSWORD = "9780360325";

    private final String DEVICE_ID = "260038000447363333343435";

    private long subscriptionId;

    private ParticleDevice mDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ParticleCloudSDK.init(getApplicationContext());

       // getDevices();


        timer = (TextView) findViewById(R.id.timer);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(20000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        timer.setText(String.valueOf(counter));
                        counter++;
                    }

                    public void onFinish() {
                        timer.setText("FINISH!!");
                    }
                }.start();
            }
        });


    }

//    public void checkTimer(View view) {
//        getFromDevice("smile");
//    }

    public void getDevices() {
        // This function runs in the background
        // It tries to connect to the Particle Cloud and get your device
        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {

            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                particleCloud.logIn(PARTICLE_USERNAME, PARTICLE_PASSWORD);
                mDevice = particleCloud.getDevice(DEVICE_ID);
                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                Log.d(TAG, "Successfully got device from Cloud");
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }
}
//
//    public void getFromDevice(final String eventDD) {
//
//        if (mDevice == null) {
//            Log.d(TAG, "Cannot find device");
//            return;
//        }
//
//        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
//
//            @Override
//            public Object callApi(ParticleCloud particleCloud) throws ParticleCloudException, IOException {
//                subscriptionId = ParticleCloudSDK.getCloud().subscribeToMyDevicesEvents(
//                        eventDD,  // the first argument, "eventNamePrefix", is optional
//                        new ParticleEventHandler() {
//                            public void onEvent(String eventName, ParticleEvent event) {
//                                Log.d(TAG, "Received event with payload: " + event.dataPayload);
//                                //   ss = (event.dataPayload).toString();
//
//                                runOnUiThread(new Thread(new Runnable() {
//                                    @Override
//                                    public void run() {
//
//                                    }
//                                }));
//                            }
//
//
//                            public void onEventError(Exception e) {
//                                Log.e(TAG, "Event error: ", e);
//                            }
//                        });
//                return -1;
//            }
//
//            @Override
//            public void onSuccess(Object o) {
//                Log.d(TAG, "Successfully got device data from Cloud");
//            }
//
//            @Override
//            public void onFailure(ParticleCloudException exception) {
//                Log.d(TAG, exception.getBestMessage());
//            }
//        });
//
//    }



