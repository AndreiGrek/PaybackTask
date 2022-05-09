package com.example.paybacktask

import android.app.Application
import com.example.paybacktask.data.RepositoryImpl
import com.example.paybacktask.data.RetrofitData
import com.example.paybacktask.domain.GetAllUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PixabyApplication : Application() {

//    private val applicationScope = CoroutineScope(SupervisorJob())

    private val retrofit by lazy { RetrofitData.getInstance()}

    //Repositories
    private val repository by lazy { RepositoryImpl(retrofit) }

    //Usecases
    val getAllUseCase by lazy { GetAllUseCase(repository) }
}