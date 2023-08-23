package com.izelhatipoglu.myweatherapp.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Insets.add
import java.util.Calendar
import java.util.Locale

object ReminderManager {

    const val REMINDER_NOTIFICATION_REQUEST_CODE = 123

    fun startReminder(
        context: Context,
        reminderTime: String = "08:31",
        reminderId: Int = REMINDER_NOTIFICATION_REQUEST_CODE,
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        reminderTime?.let {
            val (hours, min) = reminderTime.split(":").map { it.toInt() }
            val intent =
                Intent(context.applicationContext, AlarmReceiver::class.java).let { intent ->
                    PendingIntent.getBroadcast(
                        context.applicationContext,
                        reminderId,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )
                }
            val calendar: Calendar = Calendar.getInstance(Locale.ENGLISH).apply {
                set(Calendar.HOUR_OF_DAY, hours)
                set(Calendar.MINUTE, min)
            }
            if (Calendar.getInstance(Locale.ENGLISH)
                    .apply { add(Calendar.MINUTE, 1) }.timeInMillis - calendar.timeInMillis > 0
            ) {
                calendar.add(Calendar.DATE, 1)
            }

            alarmManager.setAlarmClock(
                AlarmManager.AlarmClockInfo(calendar.timeInMillis, intent),
                intent
            )
        }


    }

    fun stopReminder(
        context: Context,
        reminderId: Int = REMINDER_NOTIFICATION_REQUEST_CODE,
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(
                context,
                reminderId,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        }
        alarmManager.cancel(intent)
    }


}