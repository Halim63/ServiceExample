package com.example.serviceexample

import android.app.IntentService
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log


class MyIntentService : IntentService("MyIntentService") {

    override fun onCreate() {
        super.onCreate()
        Log.d("hh","Service created")

    }


    override fun onHandleIntent(intent: Intent?) {
        Log.d("hh","Service started")
        try {
            Thread.sleep(5000)
        }catch (e:InterruptedException){
            e.printStackTrace()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("hh","Service destroyed")

    }


}