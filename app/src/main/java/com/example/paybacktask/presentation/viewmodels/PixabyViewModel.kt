package com.example.paybacktask.presentation.viewmodels

import androidx.lifecycle.*
import com.example.paybacktask.domain.GetAllHitsUseCase
import com.example.paybacktask.domain.PixabayResponse
import kotlinx.coroutines.*
import javax.inject.Inject

class PixabyViewModel @Inject constructor(
    private val getAllHitsUseCase: GetAllHitsUseCase,
) : ViewModel() {

    private var _pixabayResponse = MutableLiveData<PixabayResponse>()
    val pixabayResponse: LiveData<PixabayResponse>
        get() = _pixabayResponse

    private var _query = MutableLiveData<String>()
    val query: LiveData<String>
        get() = _query

    fun setQuery(query: String) {
        _query.value = query
    }

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("CoroutineExceptionHandler got $throwable")
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getAllPictures(query: String) {
        job = viewModelScope.launch (Dispatchers.IO + exceptionHandler){
            val response = getAllHitsUseCase.execute(query)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _pixabayResponse.postValue(response.body())
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        _errorMessage.postValue(message)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}