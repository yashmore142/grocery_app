package com.example.ecommers.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommers.databinding.ItemBannerBinding
import com.example.ecommers.data.data_models.login.response.SliderArray

class BannerViewPagerAdapter(private val context: Context, private val sliderArray: ArrayList<SliderArray>) : RecyclerView.Adapter<BannerViewPagerAdapter.MainViewHolder>() {
    class MainViewHolder(var binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root)

    /*var dataList: List<HomeSliderData> = arrayListOf()*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBannerBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return sliderArray.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        Glide.with(context)
            .load(sliderArray[position].image)
            .into(holder.binding.bannerItem)
    }

}