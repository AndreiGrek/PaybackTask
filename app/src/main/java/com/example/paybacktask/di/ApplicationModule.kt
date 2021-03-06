package com.example.paybacktask.di

import com.example.paybacktask.data.PaybackRepositoryImpl
import com.example.paybacktask.data.RetrofitData
import com.example.paybacktask.data.RetrofitService
import com.example.paybacktask.domain.PaybackRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface ApplicationModule {

    @Binds
    fun bindPaybackRepository(impl: PaybackRepositoryImpl): PaybackRepository

    companion object {
        @Provides
        fun provideRetrofitService() : RetrofitService {
            return RetrofitData.getInstance()
        }
    }
}