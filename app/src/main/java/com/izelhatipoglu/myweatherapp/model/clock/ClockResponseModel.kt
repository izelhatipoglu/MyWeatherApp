package com.izelhatipoglu.myweatherapp.model.clock

import com.google.gson.annotations.SerializedName
import com.izelhatipoglu.myweatherapp.model.forecastResponse.ForecastConditionResponseModel

data class ClockResponseModel(
    @SerializedName("time")
    val time: String,
    @SerializedName("temp_c")
    val tempC: Float,
    @SerializedName("condition")
    val condition: ForecastConditionResponseModel
)