package com.example.ecommers.di

import com.example.ecommers.data.repository.Repository
import com.example.ecommers.data.repository.RepositoryImpl
import com.example.ecommers.network.APIClient
import com.example.ecommers.network.APIInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesAPIClient(): APIClient = APIClient

    @Provides
    @Singleton
    fun providesAPIInterface(apiClient: APIClient): APIInterface =
        apiClient.getClient()!!.create(APIInterface::class.java)

    @Provides
    @Singleton
    fun providesRepository(apiInterface: APIInterface): Repository = RepositoryImpl(apiInterface)

}