package com.izelhatipoglu.myweatherapp.ui.setting


import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.izelhatipoglu.myweatherapp.base.BaseFragmentVM
import com.izelhatipoglu.myweatherapp.databinding.FragmentSettingBinding
import com.izelhatipoglu.myweatherapp.model.location.CityInfoModel
import com.izelhatipoglu.myweatherapp.ui.setting.adapter.FavouritesCitiesAdapter
import com.izelhatipoglu.myweatherapp.ui.setting.viewModel.SettingViewModel
import com.izelhatipoglu.myweatherapp.util.AbsoluteFitLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar


@AndroidEntryPoint
class SettingFragment : BaseFragmentVM<FragmentSettingBinding, SettingViewModel>() {

    override val viewModel by viewModels<SettingViewModel>()

    private lateinit var sharedPreference : SharedPreferences
    private lateinit var  editor : SharedPreferences.Editor

    val NOTIFICATION_TIME = "time"

    private lateinit var favouritesCitiesAdapter: FavouritesCitiesAdapter

    override fun getLayoutRes() = com.izelhatipoglu.myweatherapp.R.layout.fragment_setting

    override fun initUI() {

        binding.timePicker.setIs24HourView(true)
        saveTimeToSharedPref()

        viewModel.getAllCitiesWeatherData()
        val layoutManager =
            AbsoluteFitLayoutManager(context, 2, AbsoluteFitLayoutManager.VERTICAL, false)
        favouritesCitiesAdapter = FavouritesCitiesAdapter(arrayListOf(),
            object : FavouritesCitiesAdapter.ChooseCityClickListener {
                override fun chooseCityClicked(
                    cityInfoModel: CityInfoModel,
                    isDeleteClick: Boolean,
                ) {
                    if (isDeleteClick) {
                        viewModel.deleteCityToRoom(cityInfoModel)
                    } else {
                        goToAddNewLocation()
                    }

                }
            })

        binding.recylerCities.adapter = favouritesCitiesAdapter
        binding.recylerCities.layoutManager = layoutManager
        viewModel.navController.value = findNavController()


    }

    override fun handleClick() {

    }

    override fun observeLiveData() {
        viewModel.cityLiveList.observe(viewLifecycleOwner) {
            favouritesCitiesAdapter.updateData(it)

        }

    }

    private fun saveTimeToSharedPref() {
        binding.timePicker.setOnTimeChangedListener { _, hour, minute ->
            var hour = hour
            var am_pm = ""
            // AM_PM decider logic
            when {
                hour == 0 -> {
                    hour += 12
                    am_pm = "AM"
                }

                hour == 12 -> am_pm = "PM"
                hour > 12 -> {
                    hour -= 12
                    am_pm = "PM"
                }

                else -> am_pm = "AM"
            }
            val hour2 = if (hour < 10) "0" + hour else hour
            val min = if (minute < 10) "0" + minute else minute
            // display format of time
            println("Time is: $hour2:$min $am_pm")

            sharedPreference = requireContext().getSharedPreferences("com.izelhatipoglu.myweatherapp", Context.MODE_PRIVATE)
            editor = sharedPreference.edit()
            editor.putString("time","$hour2:$min")
            editor.apply()
            println("kayıt edildi")
        }
    }

    private fun goToAddNewLocation() {
        val action = SettingFragmentDirections.actionSettingFragmentToChooseLocationFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

}