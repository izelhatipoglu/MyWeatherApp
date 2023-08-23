package com.izelhatipoglu.myweatherapp.util

import com.izelhatipoglu.myweatherapp.model.forecastResponse.ForecastMainResponseModel
import com.izelhatipoglu.myweatherapp.model.location.CityResponseModel

sealed class Resource<T>(val data:T?,val message:String?) {
    class Success<T>(data:T):Resource<T>(data,null)
    class Error<T>(message: String):Resource<T>(null,message)
}

sealed class ForecastWeatherEvent{
    class Success(val forecastMainResponseModelList: ArrayList<ForecastMainResponseModel>):ForecastWeatherEvent()
    class Failure(val errorMessage: String):ForecastWeatherEvent()
    object Loading : ForecastWeatherEvent()
}

sealed class LocationEvent{

    class Success(val locationResponseModelList: ArrayList<CityResponseModel>): LocationEvent()

    class Failure(val errorMessage: String):LocationEvent()
    object Loading : LocationEvent()
}