package com.izelhatipoglu.myweatherapp.ui.setting.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.izelhatipoglu.myweatherapp.databinding.ItemCitiesBinding
import com.izelhatipoglu.myweatherapp.model.location.CityInfoModel

class FavouritesCitiesAdapter(
    private val cityResponseList: ArrayList<CityInfoModel>,
    private val chooseCityClickListener: ChooseCityClickListener):RecyclerView.Adapter<FavouritesCitiesAdapter.FavouritesCitiesViewHolder>() {

    class FavouritesCitiesViewHolder(val binding: ItemCitiesBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): FavouritesCitiesViewHolder {
        val binding = ItemCitiesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavouritesCitiesViewHolder(binding)
    }

    override fun getItemCount() = cityResponseList.size

    override fun onBindViewHolder(holder: FavouritesCitiesViewHolder, position: Int) {
       val item = cityResponseList[position]
        holder.binding.cityTv.text = item.cityName
        holder.binding.cityTv.setSelected(true);

        if (position == cityResponseList.lastIndex){
            holder.binding.ivDelete.visibility = View.GONE
        }

        holder.binding.ivDelete.setOnClickListener {
            if(cityResponseList.size <= 2){
                Toast.makeText(
                    holder.itemView.context,
                    "You must add new city before deleting all cities",
                    Toast.LENGTH_LONG
                ).show()

            }else{
                chooseCityClickListener.chooseCityClicked(item,true)
            }
        }

        holder.binding.flRoot.setOnClickListener {
            if (position == cityResponseList.lastIndex){
                chooseCityClickListener.chooseCityClicked(item,false)
            }
        }
    }

    interface ChooseCityClickListener {
        fun chooseCityClicked(cityInfoModel: CityInfoModel,isDeleteClick: Boolean)
    }

    fun updateData(newCityList: ArrayList<CityInfoModel>){
        cityResponseList.clear()
        cityResponseList.addAll(newCityList)
        cityResponseList.add(cityResponseList.lastIndex+1,CityInfoModel(cityName = "+"))
        notifyDataSetChanged()

    }

}