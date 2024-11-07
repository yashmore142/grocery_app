package com.example.ecommers.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommers.data.product.Product
import com.example.ecommers.roomdbsetup.room_repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartViewMode @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {
    var homeErrorData: MutableLiveData<String> = MutableLiveData("")
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        homeErrorData.value = exception.localizedMessage
    }
    private var getFavouriteJob: Job? = null
    var successData: MutableLiveData<String> = MutableLiveData()
    private var _responseGetCart: MutableLiveData<List<Product>> =
        MutableLiveData(emptyList())
    val responseGetCart: LiveData<List<Product>> = _responseGetCart

    fun getCartList() {

        getFavouriteJob?.cancel()

        getFavouriteJob = viewModelScope.launch(coroutineExceptionHandler) {
            try {
                val response = withContext(Dispatchers.IO) {
                    roomRepository.getCart()
                }
                _responseGetCart.value = response
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun removeFromCart(product: Product) {
        getFavouriteJob?.cancel()

        getFavouriteJob = viewModelScope.launch(coroutineExceptionHandler) {
            try {
                val response = withContext(Dispatchers.IO) {
                    roomRepository.removeFromCart(product)
                }
                successData.value =  "Remove successfully"
                //_responseGetCart.value = response
            } catch (e: Exception) {
                throw e
            }
        }
    }
}