package com.izelhatipoglu.myweatherapp.util

import com.izelhatipoglu.myweatherapp.model.forecastResponse.ForecastMainResponseModel

sealed class Resource<T>(val data:T?,val message:String?) {
    class Success<T>(data:T):Resource<T>(data,null)
    class Error<T>(message: String):Resource<T>(null,message)
}

sealed class ForecastWeatherEvent{
    class Success(val forecastMainResponseModel: ForecastMainResponseModel):ForecastWeatherEvent()
    class Failure(val errorMessage: String):ForecastWeatherEvent()
    object Loading : ForecastWeatherEvent()
}