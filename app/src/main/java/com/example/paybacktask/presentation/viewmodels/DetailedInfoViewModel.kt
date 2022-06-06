package com.example.paybacktask.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paybacktask.domain.Hit
import javax.inject.Inject

class DetailedInfoViewModel @Inject constructor() : ViewModel() {

    private var _hitData = MutableLiveData<Hit>()
    val hitData: LiveData<Hit>
        get() = _hitData

    fun setHitData(hit: Hit) {
        _hitData.value = hit
    }
}

