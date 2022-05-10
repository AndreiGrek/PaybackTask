package com.example.paybacktask

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import com.example.paybacktask.data.RepositoryImpl
import com.example.paybacktask.data.RetrofitData
import com.example.paybacktask.domain.GetAllHitsUseCase

class PixabyApplication : Application() {

//    private val applicationScope = CoroutineScope(SupervisorJob())

    private val retrofit by lazy { RetrofitData.getInstance()}

    //Repositories
    private val repository by lazy { RepositoryImpl(retrofit) }

    //Usecases
    val getAllHitsUseCase by lazy { GetAllHitsUseCase(repository) }

    init{
        instance = this
    }

    companion object{
        private var instance: PixabyApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        val context: Context = PixabyApplication.applicationContext()
    }

}