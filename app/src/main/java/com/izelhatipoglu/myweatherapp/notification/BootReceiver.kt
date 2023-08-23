package com.izelhatipoglu.myweatherapp.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class BootReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if(p1?.action == "android.intent.action.BOOT_COMPLETED"){
            if (p0 != null) {
                ReminderManager.startReminder(p0)
            }
        }
    }
}