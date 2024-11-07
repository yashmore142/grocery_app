package com.example.ecommers.roomdbsetup.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ecommers.data.product.Product

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addProduct(data: Product)
    @Query("SELECT * FROM tbl_cart")
    fun getAllData(): List<Product>

    @Delete
    suspend fun deleteProduct(data: Product)

    @Update
    suspend fun updateProduct(data: Product)
}