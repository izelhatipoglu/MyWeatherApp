package com.izelhatipoglu.myweatherapp.model.location

import com.google.gson.annotations.SerializedName

data class CityResponseModel (
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String
        )