package com.example.ecommers.roomdbsetup.room_repository



import com.example.ecommers.data.historymodel.HistoryRoomData
import com.example.ecommers.data.product.Product
import com.example.ecommers.roomdbsetup.roomdb.CartDao
import com.example.ecommers.roomdbsetup.roomdb.HistoryDao
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(private val historyDao: HistoryDao,private  val cartDao: CartDao
) : RoomRepository {
    override suspend fun getHistory(): List<HistoryRoomData> = historyDao.getAllData()

    override suspend fun addData(history: HistoryRoomData){
        historyDao.addHistory(history) // No return type; just add the data
    }
    override suspend fun getCart(): List<Product> = cartDao.getAllData()

    override suspend fun addToCart(product: Product){
        cartDao.addProduct(product) // No return type; just add the data
    }

    override suspend fun removeFromCart(product: Product){
        cartDao.deleteProduct(product) // No return type; just add the data
    }
}