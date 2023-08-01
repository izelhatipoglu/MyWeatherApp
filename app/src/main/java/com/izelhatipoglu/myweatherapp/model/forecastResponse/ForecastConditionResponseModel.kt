package com.izelhatipoglu.myweatherapp.model.forecastResponse

import com.google.gson.annotations.SerializedName

data class ForecastConditionResponseModel (
    @SerializedName("icon")
    val icon: String
        )