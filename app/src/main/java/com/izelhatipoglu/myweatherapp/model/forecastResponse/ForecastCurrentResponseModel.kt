package com.izelhatipoglu.myweatherapp.model.forecastResponse

import com.google.gson.annotations.SerializedName

data class ForecastCurrentResponseModel(
    @SerializedName("temp_c")
    val temp_c : Float,
    @SerializedName("condition")
    val condition:ForecastConditionResponseModel

)