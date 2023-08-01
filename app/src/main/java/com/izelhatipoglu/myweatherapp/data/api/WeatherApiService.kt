package com.izelhatipoglu.myweatherapp.data.api

import com.izelhatipoglu.myweatherapp.util.Constant.API_ENDPOINT_FORECAST
import com.izelhatipoglu.myweatherapp.model.forecastResponse.ForecastMainResponseModel
import com.izelhatipoglu.myweatherapp.util.Constant
import com.izelhatipoglu.myweatherapp.util.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET(API_ENDPOINT_FORECAST)
    suspend fun getForecast(
        @Query("q") cityName: String,
        @Query("days") days: String
    ):Response<ForecastMainResponseModel>
}