package com.izelhatipoglu.myweatherapp.ui.splash

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.izelhatipoglu.myweatherapp.R
import com.izelhatipoglu.myweatherapp.base.BaseFragment
import com.izelhatipoglu.myweatherapp.base.BaseFragmentVM
import com.izelhatipoglu.myweatherapp.data.local.CityInfoDatabase
import com.izelhatipoglu.myweatherapp.databinding.FragmentHomeBinding
import com.izelhatipoglu.myweatherapp.databinding.FragmentSettingBindingImpl
import com.izelhatipoglu.myweatherapp.databinding.FragmentSplashBinding
import com.izelhatipoglu.myweatherapp.ui.splash.viewModel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {


    override fun getLayoutRes() = R.layout.fragment_splash

    private val activityScope = CoroutineScope(Dispatchers.Main)

    @Inject
    lateinit var cityInfoDatabase: CityInfoDatabase


    override fun initUI() {
    }

    override fun handleClick() {

    }

    override fun observeLiveData() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCoroutineScope()
    }

    private fun initCoroutineScope(){
        activityScope.launch {
            delay(2000)
            controlLocation()
        }
    }

    private fun controlLocation(){
        activityScope.launch {
            val cityList = cityInfoDatabase?.cityInfoDao()?.getAllCities()
            if(cityList?.size == 0){
                val action = SplashFragmentDirections.actionSplashFragmentToChooseLocationFragment()
                NavHostFragment.findNavController(this@SplashFragment).navigate(action)
            }else{
                val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
                NavHostFragment.findNavController(this@SplashFragment).navigate(action)
            }
        }
    }

}