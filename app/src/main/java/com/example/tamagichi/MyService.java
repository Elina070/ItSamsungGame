package com.example.tamagichi;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        GameSurface.Live live= new GameSurface.Live();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                live.LiveCMin();
            }
        }, 0, 10000);

    }




    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}












