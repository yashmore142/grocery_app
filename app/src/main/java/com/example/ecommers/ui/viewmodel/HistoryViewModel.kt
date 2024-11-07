package com.example.ecommers.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommers.data.historymodel.HistoryRoomData
import com.example.ecommers.roomdbsetup.room_repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {
    var homeErrorData: MutableLiveData<String> = MutableLiveData("")
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        homeErrorData.value = exception.localizedMessage
    }
    private var getFavouriteJob: Job? = null
    private var _responseGetWeather: MutableLiveData<List<HistoryRoomData>> =
        MutableLiveData(emptyList())
    val responseGetHistory: LiveData<List<HistoryRoomData>> = _responseGetWeather

    fun getHistory() {

        getFavouriteJob?.cancel()

        getFavouriteJob = viewModelScope.launch(coroutineExceptionHandler) {
            try {
                val response = withContext(Dispatchers.IO) {
                    roomRepository.getHistory()
                }
                _responseGetWeather.value = response
            } catch (e: Exception) {
                throw e
            }
        }
    }
    fun addHistory(history: HistoryRoomData) {
        getFavouriteJob?.cancel()

        getFavouriteJob = viewModelScope.launch(coroutineExceptionHandler) {
            try {
                val response = withContext(Dispatchers.IO) {
                    roomRepository.addData(history)
                }
                //_responseAddWeather.value = response
            } catch (e: Exception) {
                throw e
            }
        }
    }

}