package com.izelhatipoglu.myweatherapp.model.forecastResponse

import com.google.gson.annotations.SerializedName

data class ForecastDayResponseModel(
    @SerializedName("forecastday")
    val forecastDay: ArrayList<ForecastDayDetailResponseModel>
)