package com.example.ecommers.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommers.databinding.ItemMyCartBinding
import com.example.ecommers.data.product.Product

class CartAdapter(var context: Context, var list: List<Product>,private val removetClickListener: RemoveProductListner) :
    RecyclerView.Adapter<CartAdapter.CitiesHolder>() {
    class CitiesHolder constructor(var binding: ItemMyCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemMyCartBinding.inflate(inflater, parent, false)
        return CitiesHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CitiesHolder, position: Int) {
        holder.binding.tvproductname.setText(list[position].name)
        holder.binding.tvPackaging.setText( list[position].weight)
        holder.binding.tvPrize.setText("Rs" + " " +list[position].price)
        Glide.with(context)
            .load(list[position].imageUrl)
            .into(holder.binding.productimage)

        holder.binding.addcart.setOnClickListener {
            removetClickListener.onRemoveProductClick(list[position])
        }


    }
}
interface RemoveProductListner {
    fun onRemoveProductClick(product: Product)
}