package com.izelhatipoglu.myweatherapp.model.forecastResponse

import com.google.gson.annotations.SerializedName
import com.izelhatipoglu.myweatherapp.model.clock.ClockResponseModel
import com.izelhatipoglu.myweatherapp.model.day.DaysResponseModel

data class ForecastDayDetailResponseModel(
    @SerializedName("date")
    val date :String,
    @SerializedName("day")
    val day: DaysResponseModel,
    @SerializedName("hour")
    val hour: ArrayList<ClockResponseModel>
)