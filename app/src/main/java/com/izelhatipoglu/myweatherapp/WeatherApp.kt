package com.izelhatipoglu.myweatherapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.core.content.ContextCompat
import com.izelhatipoglu.myweatherapp.notification.ReminderManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApp : Application() {

    private lateinit var sharedPreference : SharedPreferences
    private lateinit var  editor : SharedPreferences.Editor
    override fun onCreate() {
        super.onCreate()
        createNotificationsChannels()
        ReminderManager.startReminder(this)

        getTime()
    }

    private fun createNotificationsChannels(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                getString(R.string.channel_id),
                getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            ContextCompat.getSystemService(this,NotificationManager::class.java)
                ?.createNotificationChannel(channel)
        }
    }

    fun getTime() :String {
        sharedPreference = applicationContext.getSharedPreferences("com.izelhatipoglu.myweatherapp", Context.MODE_PRIVATE)
        return println("Shared preferences ${sharedPreference.getString("time","")}").toString()
    }
}