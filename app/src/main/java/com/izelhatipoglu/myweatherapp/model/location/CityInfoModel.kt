package com.izelhatipoglu.myweatherapp.model.location

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityInfoModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var cityName: String
)