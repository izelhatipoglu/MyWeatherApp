package com.izelhatipoglu.myweatherapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izelhatipoglu.myweatherapp.databinding.ItemDayRowBinding
import com.izelhatipoglu.myweatherapp.model.forecastResponse.ForecastDayDetailResponseModel

class WeatherDayAdapter(private val daysResponseList: ArrayList<ForecastDayDetailResponseModel>): RecyclerView.Adapter<WeatherDayAdapter.WeatherDaysViewHolder>(){

   class WeatherDaysViewHolder(val binding: ItemDayRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int,
    ): WeatherDaysViewHolder{
        val binding = ItemDayRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WeatherDaysViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherDaysViewHolder, position: Int) {
        val item = daysResponseList[position+1]
        holder.binding.model = item
        holder.binding.text.isSelected = true

    }

    override fun getItemCount() = daysResponseList.size -1

}