package com.izelhatipoglu.myweatherapp.ui.home.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.izelhatipoglu.myweatherapp.R
import com.izelhatipoglu.myweatherapp.base.BaseViewModel
import com.izelhatipoglu.myweatherapp.data.local.CityInfoDatabase
import com.izelhatipoglu.myweatherapp.data.remote.repository.WeatherRepository
import com.izelhatipoglu.myweatherapp.model.forecastResponse.ForecastMainResponseModel
import com.izelhatipoglu.myweatherapp.model.location.CityInfoModel
import com.izelhatipoglu.myweatherapp.ui.home.HomeFragmentDirections
import com.izelhatipoglu.myweatherapp.util.ForecastWeatherEvent
import com.izelhatipoglu.myweatherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val weatherRepository: WeatherRepository,
    private val cityInfoDatabase: CityInfoDatabase
): BaseViewModel(application) {

    val navController = MutableLiveData<NavController>()
    private val _forecastWeather = MutableLiveData<ForecastWeatherEvent>()
    private val allWeatherData = ArrayList<ForecastMainResponseModel>()
    val forecastWeather: LiveData<ForecastWeatherEvent> get() =_forecastWeather



    private fun getWeather(cityName:String) = viewModelScope.launch {
        _forecastWeather.value= ForecastWeatherEvent.Loading
        when(val response = weatherRepository.getForecastWeather(cityName = cityName)){
            is Resource.Error-> _forecastWeather.value= ForecastWeatherEvent.Failure(response.message!!)
            is Resource.Success-> {
                allWeatherData.add(response.data!!)
                _forecastWeather.value= ForecastWeatherEvent.Success(allWeatherData)
            }

            else -> {}
        }
    }

    fun getAllCitiesWeatherData(){
        viewModelScope.launch {
            allWeatherData.clear()
            val cityList = cityInfoDatabase.cityInfoDao().getAllCities()
            if (cityList.size != 0){
                for (city in cityList){
                    getWeather(city.cityName)
                }
            }
        }
    }

    fun goSettings(){
        navController.value?.navigate(R.id.settingFragment)
    }

    fun goLocation(){
        navController.value?.navigate(R.id.chooseLocationFragment)
    }

}