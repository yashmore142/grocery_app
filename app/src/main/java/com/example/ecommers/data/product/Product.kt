package com.example.ecommers.data.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_cart")
data class Product(
    @PrimaryKey(autoGenerate = true)
                  val id: Int = 0,
                    val name: String = "",
                    val weight :String = "",
                    val description: String ="",
                    val price: Double = 0.0,
                    val imageUrl: String = "",
                    val inStock: Boolean = false )
