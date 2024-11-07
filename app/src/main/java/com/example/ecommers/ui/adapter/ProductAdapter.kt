package com.example.ecommers.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommers.databinding.ItemProductBinding
import com.example.ecommers.data.product.Product

class ProductAdapter(var context: Context, var list: List<Product>, private val productClickListener: ProductClickListener) :
    RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    class ProductHolder constructor(var binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.binding.tvproductname.setText(list[position].name)
        holder.binding.tvPackaging.setText( list[position].weight)
        holder.binding.tvPrize.setText("Rs ." +  list[position].price)
        Glide.with(context)
            .load(list[position].imageUrl)
            .into(holder.binding.productimage)

        holder.binding.addcart.setOnClickListener {
            productClickListener.onAddProductClick(list[position])
        }

    }
}
interface ProductClickListener {
    fun onAddProductClick(product: Product)
}
