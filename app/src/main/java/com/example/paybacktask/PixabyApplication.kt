package com.example.paybacktask

import android.app.Application
import android.content.Context
import com.example.paybacktask.data.PaybackRepositoryImpl
import com.example.paybacktask.data.RetrofitData
import com.example.paybacktask.di.DaggerApplicationComponent
import com.example.paybacktask.domain.GetAllHitsUseCase

class PixabyApplication : Application() {

    //Dagger
    val component by lazy {
        DaggerApplicationComponent.create()
    }


    init{
        instance = this
    }

    companion object{
        private var instance: PixabyApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}