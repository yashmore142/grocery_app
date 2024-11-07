package com.example.ecommers.roomdbsetup.room_repository

import com.example.ecommers.data.historymodel.HistoryRoomData
import com.example.ecommers.data.product.Product


interface RoomRepository {
    suspend fun getHistory () :List<HistoryRoomData>

    suspend fun addData(history: HistoryRoomData)

    suspend fun  getCart() : List<Product>

    suspend fun addToCart(product: Product)
    suspend fun removeFromCart(product: Product)
}