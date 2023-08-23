package com.izelhatipoglu.myweatherapp.ui.setting.viewModel

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.izelhatipoglu.myweatherapp.R
import com.izelhatipoglu.myweatherapp.base.BaseViewModel
import com.izelhatipoglu.myweatherapp.data.local.CityInfoDatabase
import com.izelhatipoglu.myweatherapp.model.location.CityInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    application: Application,
    private val cityInfoDatabase: CityInfoDatabase
): BaseViewModel(application){

    val navController = MutableLiveData<NavController>()
    val cityLiveList = MutableLiveData<ArrayList<CityInfoModel>>()

    fun backHome(){
        navController.value?.navigate(R.id.homeFragment)
    }


    fun getAllCitiesWeatherData(){
        viewModelScope.launch {
            val cityList = cityInfoDatabase.cityInfoDao().getAllCities()
            if (cityList.size != 0){
                cityLiveList.value = cityList as ArrayList<CityInfoModel>
            }
        }
    }

    fun deleteCityToRoom(cityInfoModel: CityInfoModel){
        viewModelScope.launch {
            cityInfoDatabase.cityInfoDao().deleteCity(cityInfoModel)
            getAllCitiesWeatherData()
        }
    }

}