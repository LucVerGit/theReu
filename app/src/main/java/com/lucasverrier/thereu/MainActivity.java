package com.lucasverrier.thereu;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Application;
import android.content.Context;

import net.danlew.android.joda.JodaTimeAndroid;

public class MainActivity extends Application {


    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        MainActivity.mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}