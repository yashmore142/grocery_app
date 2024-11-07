package com.example.ecommers.roomdbsetup.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ecommers.data.historymodel.HistoryRoomData
import com.example.ecommers.data.product.Product

@Database(entities = [HistoryRoomData::class , Product::class], version = 1, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
    abstract fun cartDao():CartDao

    companion object {

        @Volatile
        private var INSTANCE: HistoryDataBase? = null

        fun getDataBase(context: Context): HistoryDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HistoryDataBase::class.java,
                    "my_database"
                ).build()


                INSTANCE = instance
                return instance
            }
        }
    }
}