package com.example.ecommers.ui.adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommers.R
import com.example.ecommers.databinding.BestSellerLayoutBinding
import com.example.ecommers.databinding.ClothingLayoutBinding
import com.example.ecommers.data.data_models.login.response.ProductDetailsResponse

class CategoryChildAdapter(
    private val viewType: Int,
    private val recyclerItemList: List<ProductDetailsResponse>,
    private val  requireContext: Context
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class BestSellerViewHolder(private val binding: BestSellerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindBestSellerView(recyclerItem: ProductDetailsResponse) {
            binding.bestSellerText.text = recyclerItem.name
            Glide.with(requireContext)
                .load(recyclerItem.image)
                .into(binding.bestSellerImage)
        }
    }

    inner class ClothingViewHolder(private val binding: ClothingLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindClothingView(recyclerItem: ProductDetailsResponse) {
         //   binding.clothingImage.setImageResource(recyclerItem.image)
            binding.clothingOfferTv.text = recyclerItem.name
            Glide.with(requireContext)
                .load(recyclerItem.image)
                .into(binding.clothingImage)
           /* binding.clothingImage.setOnClickListener{
                showDialog(recyclerItem.image,binding.root.context)
            }*/
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            DataItemType.BEST_SELLING_ITEMS -> {
                val binding = BestSellerLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return BestSellerViewHolder(binding)
            }
            else -> {
                val binding = ClothingLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ClothingViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return recyclerItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {

            is BestSellerViewHolder -> {
                holder.bindBestSellerView(recyclerItemList[position])
            }

            is ClothingViewHolder -> {
                holder.bindClothingView(recyclerItemList[position])
            }
        }
    }

    private fun showDialog(image: Int, activity: Context) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dialog_layout)
        val imgShow = dialog.findViewById(R.id.img_show) as ImageView
        imgShow.setImageResource(image)
        dialog.show()

    }
}