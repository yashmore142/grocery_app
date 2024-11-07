package com.example.ecommers.roomdbsetup

import android.content.Context
import com.example.ecommers.roomdbsetup.room_repository.RoomRepository
import com.example.ecommers.roomdbsetup.room_repository.RoomRepositoryImpl
import com.example.ecommers.roomdbsetup.roomdb.CartDao
import com.example.ecommers.roomdbsetup.roomdb.HistoryDao
import com.example.ecommers.roomdbsetup.roomdb.HistoryDataBase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
 /*   @Provides
    @Singleton
    fun providesAPIInterface(): APIInterface =
        APIClient.getClient()!!.create(APIInterface::class.java)

    @Provides
    @Singleton
    fun providesRepository(apiInterface: APIInterface): Repository {
        return RepositoryImpl(apiInterface)
    }

    @Provides
    @Singleton
    fun providesSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }
    */

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): HistoryDataBase {
        return HistoryDataBase.getDataBase(context)
    }
    @Provides
    @Singleton
    fun providesWeatherDao(db: HistoryDataBase): HistoryDao {
        return db.historyDao()
    }
    @Provides
    @Singleton
    fun providesCartDao(db: HistoryDataBase): CartDao {
        return db.cartDao()
    }

    @Provides
    @Singleton
    fun providesRoomRepository(historyDao: HistoryDao,cartDao: CartDao): RoomRepository {
        return RoomRepositoryImpl(historyDao,cartDao)
    }
}