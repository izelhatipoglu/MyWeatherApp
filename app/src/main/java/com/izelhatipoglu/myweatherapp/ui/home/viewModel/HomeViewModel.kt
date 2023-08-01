package com.izelhatipoglu.myweatherapp.ui.home.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.izelhatipoglu.myweatherapp.base.BaseViewModel
import com.izelhatipoglu.myweatherapp.data.repository.WeatherRepository
import com.izelhatipoglu.myweatherapp.model.forecastResponse.ForecastMainResponseModel
import com.izelhatipoglu.myweatherapp.util.ForecastWeatherEvent
import com.izelhatipoglu.myweatherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val weatherRepository: WeatherRepository
): BaseViewModel(application) {

    private val _forecastWeather = MutableLiveData<ForecastWeatherEvent>()
    val forecastWeather: LiveData<ForecastWeatherEvent> get() =_forecastWeather



    private fun getWeather(cityName:String,days: String) = viewModelScope.launch {
        _forecastWeather.value= ForecastWeatherEvent.Loading
        when(val response = weatherRepository.fetchForecastWeather(cityName = cityName, days = days)){
            is Resource.Error-> _forecastWeather.value= ForecastWeatherEvent.Failure(response.message!!)
            is Resource.Success-> _forecastWeather.value= ForecastWeatherEvent.Success(response.data!!)
        }
    }

    fun setValues(cityName: String,days: String) {
        if(forecastWeather.value!=null)
            return
        getWeather(cityName,days)
      //  getUpcomingWeather(latitude, longitude)
    }


}