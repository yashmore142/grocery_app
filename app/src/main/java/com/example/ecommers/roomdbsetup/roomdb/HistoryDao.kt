package com.example.ecommers.roomdbsetup.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ecommers.data.historymodel.HistoryRoomData

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addHistory(data: HistoryRoomData)
    @Query("SELECT * FROM tbl_history")
    fun getAllData(): List<HistoryRoomData>

    @Delete
    suspend fun deleteWeather(data: HistoryRoomData)

    @Update
    suspend fun updateWeather(data: HistoryRoomData)

}