package com.example.manageme.presenter


import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color


import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.createDeviceProtectedStorageContext

import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService

import com.example.manageme.R

class PushNotification(c : Context)   {
    val c = c
    companion object{
        val CHANNEL_ID = "CHANNEL_1"
        val CHANNEL_NAME = "CHANNEL_NAME"
        val NOTIFICATION_ID = 0
    }

    fun shownotification(){
        val NotificationManager = NotificationManagerCompat.from(c)
        NotificationManager.notify(NOTIFICATION_ID,createnotification())
    }

    fun createnotification() : Notification{
        val notification = NotificationCompat.Builder(c, CHANNEL_ID)
                .setContentText("test")
                .setContentTitle("test")
                .setSmallIcon(R.drawable.ckbtn_setting_selector)
                .setColor(Color.GREEN)
                .setColorized(true)
                .build()

        return notification
    }

    fun createnotificationchannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT)
            val manager = c.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

    }

}