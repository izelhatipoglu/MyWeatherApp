package com.izelhatipoglu.myweatherapp.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.izelhatipoglu.myweatherapp.util.Constant
import javax.inject.Inject

class UnitProvider @Inject constructor( private val context: Context) {

    private val preferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(context)

    fun getLocation():String {
        val selectedLocation = preferences.getString(Constant.LOCATION_KEY, null)
        return selectedLocation ?: Constant.DEFAULT_LOCATION_VALUE
    }

    fun getDays():String{
        val days = preferences.getString(Constant.DAYS_KEY,null)
        return days?: Constant.DEFAULT_DAYS_VALUE
    }

}