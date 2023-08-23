package com.izelhatipoglu.myweatherapp.ui.chooseLocation.viewModel

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.izelhatipoglu.myweatherapp.R

import com.izelhatipoglu.myweatherapp.base.BaseViewModel
import com.izelhatipoglu.myweatherapp.data.local.CityInfoDatabase
import com.izelhatipoglu.myweatherapp.data.remote.repository.WeatherRepository
import com.izelhatipoglu.myweatherapp.model.location.CityInfoModel
import com.izelhatipoglu.myweatherapp.model.location.CityResponseModel
import com.izelhatipoglu.myweatherapp.util.ForecastWeatherEvent
import com.izelhatipoglu.myweatherapp.util.LocationEvent
import com.izelhatipoglu.myweatherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseLocationViewModel @Inject constructor(
    application: Application,
    private val weatherRepository: WeatherRepository,
    private val cityInfoDatabase: CityInfoDatabase
):BaseViewModel(application) {

    private val _location = MutableLiveData<LocationEvent>()

    val location: LiveData<LocationEvent> get() = _location

    private fun getLocation(cityName:String) = viewModelScope.launch {
        _location.value = LocationEvent.Loading
        when(val response = weatherRepository.getLocation(location = cityName)){
            is Resource.Error ->_location.value =LocationEvent.Failure(response.message!!)
            is Resource.Success ->_location.value =LocationEvent.Success(response.data!!)
        }
    }

    fun setValue(cityName: String){
        getLocation(cityName)
    }

    fun saveCityToRoom(cityInfoModel: CityInfoModel){
        viewModelScope.launch {
            cityInfoDatabase.cityInfoDao().insertCity(cityInfoModel)
        }
    }


}