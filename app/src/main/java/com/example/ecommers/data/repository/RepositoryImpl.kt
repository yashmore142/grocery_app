package com.example.ecommers.data.repository


import com.example.ecommers.data.data_models.login.response.HomeResponse
import com.example.ecommers.network.APIInterface

class RepositoryImpl(
    private val apiInterface: APIInterface
) : Repository {
    override suspend fun getHomeData(): HomeResponse {
        return apiInterface.userLogin()
    }



}