package com.izelhatipoglu.myweatherapp.model.day

import com.google.gson.annotations.SerializedName
import com.izelhatipoglu.myweatherapp.model.forecastResponse.ForecastConditionResponseModel

data class DaysResponseModel(
    @SerializedName("date")
    val date: String,
    @SerializedName("avgtemp_c")
    val avgtemp_c: Float,
    @SerializedName("condition")
    val condition: ForecastConditionResponseModel
)