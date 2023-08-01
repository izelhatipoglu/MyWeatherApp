package com.izelhatipoglu.myweatherapp.model.forecastResponse

import com.google.gson.annotations.SerializedName

data class ForecastResponseModel(
    @SerializedName("name")
    val name: String,

    @SerializedName("localtime")
    val localTimme: String
)