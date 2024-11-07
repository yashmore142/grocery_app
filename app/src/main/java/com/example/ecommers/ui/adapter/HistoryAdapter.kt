package com.example.ecommers.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommers.databinding.ItemHistoryBinding
import com.example.ecommers.data.historymodel.HistoryRoomData

class HistoryAdapter(var context: Context, var list: List<HistoryRoomData>) :
    RecyclerView.Adapter<HistoryAdapter.CitiesHolder>() {
    class CitiesHolder constructor(var binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemHistoryBinding.inflate(inflater, parent, false)
        return CitiesHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CitiesHolder, position: Int) {
        holder.binding.tvOrderId.setText("Order Id : "+list[position].orderId)
        holder.binding.tvdate.setText("Date : " +  list[position].date)

    }
}