package com.izelhatipoglu.myweatherapp.di

import android.content.Context
import com.izelhatipoglu.myweatherapp.sharedPreferences.UnitProvider
import dagger.Module
import dagger.Provides

import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {

    @Singleton
    @Provides
    fun provideUnitProvider(
        @ApplicationContext context: Context
    ) = UnitProvider(context)
}