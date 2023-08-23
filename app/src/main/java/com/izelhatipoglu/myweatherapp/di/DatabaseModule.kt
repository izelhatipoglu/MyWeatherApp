package com.izelhatipoglu.myweatherapp.di

import android.content.Context
import androidx.room.Room
import com.izelhatipoglu.myweatherapp.data.local.CityInfoDao
import com.izelhatipoglu.myweatherapp.data.local.CityInfoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideCityInfoDao(cityInfoDatabase: CityInfoDatabase): CityInfoDao {
        return cityInfoDatabase.cityInfoDao()
    }

    @Provides
    @Singleton
    fun provideCityInfoDatabase(
       @ApplicationContext context: Context
    ) : CityInfoDatabase {
        return Room.databaseBuilder(
            context,
            CityInfoDatabase::class.java,
            "cities.db"
        ).build()
    }
}