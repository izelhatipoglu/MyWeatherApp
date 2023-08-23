package com.izelhatipoglu.myweatherapp.ui.home



import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.izelhatipoglu.myweatherapp.R
import com.izelhatipoglu.myweatherapp.base.BaseFragmentVM
import com.izelhatipoglu.myweatherapp.data.local.CityInfoDatabase
import com.izelhatipoglu.myweatherapp.databinding.FragmentHomeBinding
import com.izelhatipoglu.myweatherapp.model.location.CityInfoModel
import com.izelhatipoglu.myweatherapp.ui.home.adapter.WeatherPagerAdapter
import com.izelhatipoglu.myweatherapp.ui.home.viewModel.HomeViewModel
import com.izelhatipoglu.myweatherapp.ui.setting.SettingFragmentDirections
import com.izelhatipoglu.myweatherapp.util.ForecastWeatherEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragmentVM<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel by viewModels<HomeViewModel>()

    private lateinit var weatherPagerAdapter: WeatherPagerAdapter

    override fun getLayoutRes() = R.layout.fragment_home

    override fun initUI() {
        viewModel.getAllCitiesWeatherData()
        weatherPagerAdapter = WeatherPagerAdapter(arrayListOf())
        binding.viewPager.adapter = weatherPagerAdapter
        viewModel.navController.value = findNavController()
    }

    override fun handleClick() {
    }

    override fun observeLiveData() {

        viewModel.forecastWeather.observe(viewLifecycleOwner){ forecastWeatherEvent ->
            when (forecastWeatherEvent) {
                is ForecastWeatherEvent.Success -> {
                    weatherPagerAdapter.getNewData(forecastWeatherEvent.forecastMainResponseModelList)
                }
                is ForecastWeatherEvent.Failure -> {
                    println(forecastWeatherEvent.errorMessage)
                }
                is ForecastWeatherEvent.Loading -> println("loading")
                else -> Unit
            }
        }
    }

}