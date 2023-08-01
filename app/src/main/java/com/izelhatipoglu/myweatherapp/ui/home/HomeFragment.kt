package com.izelhatipoglu.myweatherapp.ui.home

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.izelhatipoglu.myweatherapp.R
import com.izelhatipoglu.myweatherapp.base.BaseFragmentVM
import com.izelhatipoglu.myweatherapp.databinding.FragmentHomeBinding
import com.izelhatipoglu.myweatherapp.ui.home.viewModel.HomeViewModel
import com.izelhatipoglu.myweatherapp.util.ForecastWeatherEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragmentVM<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel by viewModels<HomeViewModel>()

    override fun getLayoutRes() = R.layout.fragment_home


    override fun initUI() {

    }

    override fun handleClick() {

    }

    override fun observeLiveData() {
        viewModel.setValues("Antalya","14")
        viewModel.forecastWeather.observe(viewLifecycleOwner){ forecastWeatherEvent ->
            when (forecastWeatherEvent) {
                is ForecastWeatherEvent.Success -> {
                  //  binding.progressBarCyclic.visibility = View.GONE
                 //   setUpView(currentWeatherEvent.currentWeather)
                    println(forecastWeatherEvent.forecastMainResponseModel.current)
                }
                is ForecastWeatherEvent.Failure -> {
                 //   binding.progressBarCyclic.visibility = View.GONE
                    println(forecastWeatherEvent.errorMessage)
                    Toast.makeText(requireContext(), forecastWeatherEvent.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ForecastWeatherEvent.Loading -> println("loading") //binding.progressBarCyclic.visibility = View.VISIBLE
                else -> Unit
            }
        }
    }



}