package com.example.serviceexample

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationCompat

class MyService : Service() {
    val CHANNEL_ID="x_channelId"

    private lateinit var mediaPlayer:MediaPlayer
    override fun onCreate() {
        super.onCreate()
        mediaPlayer=MediaPlayer.create(this, R.raw.quran)
        mediaPlayer.setOnCompletionListener {
            stopSelf()
        }
        Log.d("hh","Service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1 , getNotifications())
       if (!mediaPlayer.isPlaying)
        mediaPlayer.start()
        Log.d("hh","Service started")
        return START_NOT_STICKY
//        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer.isPlaying){
            mediaPlayer.stop()
            mediaPlayer.release()
        }
        Log.d("hh","Service destroyed")

    }

     override fun onBind(p0: Intent?): IBinder? {
         TODO("Not yet implemented")
     }

      fun getNotifications():Notification {
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             val channel = NotificationChannel(CHANNEL_ID, "Channel display name",
                 NotificationManager.IMPORTANCE_DEFAULT)
             val notificationManager = getSystemService(NotificationManager::class.java)
             notificationManager!!.createNotificationChannel(channel)
         }
         val intent=Intent(this,MainActivity::class.java)
         val pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE)
         val  builder = NotificationCompat.Builder(this,CHANNEL_ID)
              .setContentTitle("my title")
             .setContentText("plah lplfplflf dfffff")
             .setSmallIcon(R.drawable.ic_launcher_foreground)
             .setPriority(NotificationCompat.PRIORITY_HIGH)
             .setContentIntent(pendingIntent)
             .addAction(R.drawable.ic_launcher_background,"Replay",pendingIntent)
         return builder.build()
     }


 }