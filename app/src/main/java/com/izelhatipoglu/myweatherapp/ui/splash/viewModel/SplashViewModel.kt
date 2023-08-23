package com.izelhatipoglu.myweatherapp.ui.splash.viewModel

import android.app.Application
import com.izelhatipoglu.myweatherapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    application: Application
): BaseViewModel(application) {

}