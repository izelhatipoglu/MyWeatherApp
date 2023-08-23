package com.izelhatipoglu.myweatherapp.ui.chooseLocation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izelhatipoglu.myweatherapp.databinding.ItemChooseCityBinding
import com.izelhatipoglu.myweatherapp.model.location.CityResponseModel


class ChooseLocationAdapter(
    private val cityChooseResponseList: ArrayList<CityResponseModel>,
    private val cityClickListener: CityClickListener
    ): RecyclerView.Adapter<ChooseLocationAdapter.ChooseLocationViewHolder>() {

    class ChooseLocationViewHolder(val binding: ItemChooseCityBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): ChooseLocationViewHolder {
        val binding = ItemChooseCityBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ChooseLocationViewHolder(binding)

    }

    override fun getItemCount() = cityChooseResponseList.size

    override fun onBindViewHolder(holder: ChooseLocationViewHolder, position: Int) {
        val item = cityChooseResponseList[position]
        holder.binding.cityTv.text = item.name
        holder.binding.countryTv.text =item.country

        holder.binding.llRecyler.setOnClickListener {
            cityClickListener.chooseCity(item.name,item.country)
        }
    }

    fun updateList(cityChooseResponseNewList: ArrayList<CityResponseModel>){
        cityChooseResponseList.clear()
        cityChooseResponseList.addAll(cityChooseResponseNewList)
        notifyDataSetChanged()
    }

    interface CityClickListener{
        fun chooseCity(cityName:String,countryName: String)
    }
}
