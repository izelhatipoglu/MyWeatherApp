package com.izelhatipoglu.myweatherapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.izelhatipoglu.myweatherapp.model.location.CityInfoModel

@Dao
interface CityInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(cityInfoModel: CityInfoModel)

    @Delete
    suspend fun deleteCity(cityInfoModel: CityInfoModel)

    @Query("SELECT * FROM cities")
    suspend fun getAllCities(): List<CityInfoModel>
}