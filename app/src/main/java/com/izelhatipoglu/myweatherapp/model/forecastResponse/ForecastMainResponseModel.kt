package com.izelhatipoglu.myweatherapp.model.forecastResponse

import com.google.gson.annotations.SerializedName

data class ForecastMainResponseModel(
    @SerializedName("location")
    val location : ForecastResponseModel,
    @SerializedName("current")
    val current: ForecastCurrentResponseModel
)