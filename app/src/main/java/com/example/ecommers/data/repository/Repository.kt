package com.example.ecommers.data.repository


import com.example.ecommers.data.data_models.login.response.HomeResponse

interface Repository {

    suspend fun getHomeData(): HomeResponse


}