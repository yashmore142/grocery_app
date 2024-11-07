package com.example.ecommers.network


import com.example.ecommers.data.data_models.login.response.HomeResponse
import retrofit2.http.POST

interface APIInterface {

    @POST("home.php")
    suspend fun userLogin(): HomeResponse

}