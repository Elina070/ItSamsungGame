package com.example.tamagichi;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    SharedPreferences settings;
GameSurface.Live live=new GameSurface.Live();
    private static final String PREFS_FILE = "D";
    private static final String PREF_NAME = "GAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameSurface(this));
        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    public void saveName() {
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putInt(PREF_NAME, live.getLive());
        prefEditor.commit();
    }

    public void getName() {
        int name = settings.getInt(PREF_NAME, live.getLive());
        live.setLive(name);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getName();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveName();
        Toast.makeText(getApplicationContext(),"сохранение, не забывайте о своём питомце",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}