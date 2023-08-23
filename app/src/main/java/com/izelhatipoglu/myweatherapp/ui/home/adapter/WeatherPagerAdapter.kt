package com.izelhatipoglu.myweatherapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izelhatipoglu.myweatherapp.databinding.ItemWeatherPagerBinding
import com.izelhatipoglu.myweatherapp.model.forecastResponse.ForecastMainResponseModel

class WeatherPagerAdapter(private val forecastMainResponseModelList: ArrayList<ForecastMainResponseModel>):RecyclerView.Adapter<WeatherPagerAdapter.WeatherViewHolder>() {

    private var forecastDataList = forecastMainResponseModelList

    class WeatherViewHolder(val binding: ItemWeatherPagerBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemWeatherPagerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WeatherViewHolder(binding)
    }

    override fun getItemCount() = forecastDataList.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
            holder.binding.model = forecastDataList[position]
            holder.binding.tvWeather.isSelected = true
           // val hourlyData = forecastDataList[position].forecast.forecastDay

                //ClockRecycler
                holder.binding.recylerViewClock.adapter = WeatherClockAdapter(forecastDataList[position].forecast.forecastDay[0].hour)

                //DailyRecycler
                holder.binding.recylerViewDay.adapter = WeatherDayAdapter(forecastDataList[position].forecast.forecastDay)


    }

    fun getNewData(newDataList: ArrayList<ForecastMainResponseModel>){
        forecastDataList.clear()
        forecastDataList.addAll(newDataList)
        notifyDataSetChanged()
    }




}