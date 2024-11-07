package com.example.ecommers.data.historymodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_history")
data class HistoryRoomData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var date: String = "",
    var status: Boolean = false,
    var orderId: String = "",

)
