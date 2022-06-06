package com.example.paybacktask.di

import androidx.lifecycle.ViewModel
import com.example.paybacktask.presentation.viewmodels.DetailedInfoViewModel
import com.example.paybacktask.presentation.viewmodels.PixabyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface ViewModelModule {

    @IntoMap
    @StringKey("PixabyViewModel")
    @Binds
    fun bindPixabyViewModel(impl: PixabyViewModel): ViewModel

    @IntoMap
    @StringKey("DetailedInfoViewModel")
    @Binds
    fun bindDetailedInfoViewModel(impl: DetailedInfoViewModel): ViewModel

}