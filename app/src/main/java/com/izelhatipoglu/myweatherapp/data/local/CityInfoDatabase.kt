package com.izelhatipoglu.myweatherapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.izelhatipoglu.myweatherapp.model.location.CityInfoModel

@Database(entities = [CityInfoModel::class], version = 1)
abstract class CityInfoDatabase : RoomDatabase() {

    abstract fun cityInfoDao(): CityInfoDao

}