package com.example.paybacktask.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paybacktask.domain.GetAllHitsUseCase
import com.example.paybacktask.domain.PixabayResponse
import kotlinx.coroutines.*

class PixabyViewModel(
    private val getAllHitsUseCase: GetAllHitsUseCase,
) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    var pixabayResponse = MutableLiveData<PixabayResponse>()

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("CoroutineExceptionHandler got $throwable")
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getAllPictures(query: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = getAllHitsUseCase.execute(query)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    pixabayResponse.postValue(response.body())
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }


    private fun onError(message: String) {
        errorMessage.postValue(message)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    class PixabyViewModelFactory(
        private val getAllHitsUseCase: GetAllHitsUseCase
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PixabyViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PixabyViewModel(
                    getAllHitsUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}