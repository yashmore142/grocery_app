package com.example.ecommers.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommers.data.data_models.login.response.HomeResponse
import com.example.ecommers.data.product.Product
import com.example.ecommers.data.repository.Repository
import com.example.ecommers.roomdbsetup.room_repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,private val roomRepository: RoomRepository
) : ViewModel() {

    private var homeJob: Job? = null
    private var responseHome: MutableLiveData<HomeResponse> = MutableLiveData()
    val _responseHome: LiveData<HomeResponse> = responseHome

    var errorData: MutableLiveData<String> = MutableLiveData()
    var successData: MutableLiveData<String> = MutableLiveData()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        errorData.value = exception.localizedMessage
    }


    fun getHomeInfo() {
        homeJob?.cancel()
        homeJob = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val loginDeferred = viewModelScope.async {
                repository.getHomeData()
            }
            withContext(Dispatchers.Main) {
                try {
                    responseHome.value = loginDeferred.await()
                } catch (e: Exception) {
                    errorData.value = e.message
                }
            }
        }
    }

    fun addProductToCart(product: Product) {
        homeJob?.cancel()

        homeJob = viewModelScope.launch(coroutineExceptionHandler) {
            try {
                val response = withContext(Dispatchers.IO) {
                    roomRepository.addToCart(product)
                }
                successData.value =  "Added successfully"
                //_responseAddWeather.value = response
            } catch (e: Exception) {
                throw e
            }
        }
    }

}