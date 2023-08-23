package com.izelhatipoglu.myweatherapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izelhatipoglu.myweatherapp.databinding.ItemClockRowBinding
import com.izelhatipoglu.myweatherapp.model.clock.ClockResponseModel

class WeatherClockAdapter(private val clockResponseList: ArrayList<ClockResponseModel>): RecyclerView.Adapter<WeatherClockAdapter.WeatherClockViewHolder>() {

    class WeatherClockViewHolder(val binding: ItemClockRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherClockViewHolder {
        val binding = ItemClockRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WeatherClockViewHolder(binding)
    }

    override fun getItemCount()  = clockResponseList.size

    override fun onBindViewHolder(holder: WeatherClockViewHolder, position: Int) {
        val item = clockResponseList[position]
        holder.binding.model = item

    }

}