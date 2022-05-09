package com.example.paybacktask.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paybacktask.domain.GetAllUseCase
import com.example.paybacktask.domain.PixabayResponse
import kotlinx.coroutines.*

class PixabyViewModel(
    private val getAllUseCase: GetAllUseCase,
) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    var pixabayResponse = MutableLiveData<PixabayResponse>()

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("CoroutineExceptionHandler got $throwable")
        onError("Exception handled: ${throwable.localizedMessage}")
//        personListFromServer = getAllPersonFromDBUseCase.execute().asLiveData() as MutableLiveData
    }
//    val loading = MutableLiveData<Boolean>()

    fun getAllPictures() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = getAllUseCase.execute()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
//                    loading.value = true
//                    delay(1000)
                    pixabayResponse.postValue(response.body())
//                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }



    private fun onError(message: String) {
//        loading.postValue(true)
        errorMessage.postValue(message)
//        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    class PixabyViewModelFactory(
        private val getAllUseCase: GetAllUseCase
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PixabyViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PixabyViewModel(
                    getAllUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}