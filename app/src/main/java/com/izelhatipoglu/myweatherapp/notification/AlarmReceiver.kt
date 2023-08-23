package com.izelhatipoglu.myweatherapp.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.izelhatipoglu.myweatherapp.R
import com.izelhatipoglu.myweatherapp.ui.MainActivity
import com.izelhatipoglu.myweatherapp.util.Constant.NOTIFICATION_ID


class AlarmReceiver : BroadcastReceiver(){

    override fun onReceive(p0: Context?, p1: Intent?) {
        val notificationManager =
            ContextCompat.getSystemService(
                p0!!,
                NotificationManager::class.java
            ) as NotificationManager

        notificationManager.sendReminderNotification(
            applicationContext = p0,
            channelId = p0.getString(R.string.channel_id)
        )
      //  ReminderManager.startReminder(p0.applicationContext)
        }

}

fun NotificationManager.sendReminderNotification(
    applicationContext: Context,
    channelId: String,
){
    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        applicationContext,
        1,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT

    )
    val builder = NotificationCompat.Builder(applicationContext,channelId)
        .setContentTitle(applicationContext.getString(R.string.title_notification))
        .setContentText(applicationContext.getString(R.string.description_notification))
        .setSmallIcon(R.drawable.logo_small)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(applicationContext.getString(R.string.description_notification))

        )
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
    notify(NOTIFICATION_ID, builder.build())


}
