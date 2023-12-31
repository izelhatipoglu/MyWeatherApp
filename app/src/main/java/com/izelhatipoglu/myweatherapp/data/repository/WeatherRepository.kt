package com.izelhatipoglu.myweatherapp.data.repository

import com.izelhatipoglu.myweatherapp.data.api.WeatherApiService
import com.izelhatipoglu.myweatherapp.model.forecastResponse.ForecastMainResponseModel
import com.izelhatipoglu.myweatherapp.util.Resource
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherApiService: WeatherApiService) {

    suspend fun fetchForecastWeather(cityName: String, days:String): Resource<ForecastMainResponseModel>{
        return try {
            val response = weatherApiService.getForecast(cityName = cityName, days = days)
            val result = response.body()
            if(response.isSuccessful && result!=null){
                Resource.Success(result)
            }else Resource.Error("Unexpected error occurred.")
        }catch (e: Exception){
            Resource.Error(e.localizedMessage ?: "Network error occurred.")
        }
    }
}
/*
suspend fun getWeather(cityName : String):Resource<CurrentWeather> {
    return  try {
        val response = apiService.getCurrentWeather(cityName,Constants.API_KEY)
        val result = response.body()
        if(response.isSuccessful&&result!=null){
            Resource.Success(result)
        }else Resource.Error("Unexpected error occurred.")
    }catch (e:Exception){
        Resource.Error("Network error occurred.")
    }
}

 */