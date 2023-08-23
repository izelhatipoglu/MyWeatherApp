package com.izelhatipoglu.myweatherapp.ui.chooseLocation

import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.izelhatipoglu.myweatherapp.R
import com.izelhatipoglu.myweatherapp.base.BaseFragmentVM
import com.izelhatipoglu.myweatherapp.data.local.CityInfoDatabase
import com.izelhatipoglu.myweatherapp.databinding.FragmentChooseLocationBinding
import com.izelhatipoglu.myweatherapp.model.location.CityInfoModel
import com.izelhatipoglu.myweatherapp.ui.chooseLocation.adapter.ChooseLocationAdapter
import com.izelhatipoglu.myweatherapp.ui.chooseLocation.viewModel.ChooseLocationViewModel
import com.izelhatipoglu.myweatherapp.util.LocationEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseLocationFragment : BaseFragmentVM<FragmentChooseLocationBinding,ChooseLocationViewModel>() {

    override val viewModel by viewModels<ChooseLocationViewModel>()

    override fun getLayoutRes() = R.layout.fragment_choose_location

    private lateinit var chooseLocationAdapter: ChooseLocationAdapter

    override fun initUI() {
        chooseLocationAdapter = ChooseLocationAdapter(arrayListOf(),object : ChooseLocationAdapter.CityClickListener{
            override fun chooseCity(cityName: String, countryName: String) {
                viewModel.saveCityToRoom(CityInfoModel(cityName = cityName))
                goToHome()
            }

        })
        binding.recylerCities.adapter = chooseLocationAdapter
        binding.recylerCities.layoutManager = LinearLayoutManager(context)

    }

    override fun handleClick() {
        binding.searchEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                viewModel.setValue(p0.toString())
            }

        })
    }

    override fun observeLiveData() {
        viewModel.location.observe(viewLifecycleOwner){ locationEvent ->
            when (locationEvent) {
                is LocationEvent.Success -> {
                    chooseLocationAdapter.updateList(locationEvent.locationResponseModelList)
                }
                is LocationEvent.Failure -> {
                    println(locationEvent.errorMessage)
                }
                is LocationEvent.Loading -> println("loading")
                else -> Unit
            }
        }


    }



    private fun goToHome(){
        val action = ChooseLocationFragmentDirections.actionChooseLocationFragmentToHomeFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

}