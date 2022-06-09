package com.example.paybacktask.di

import android.app.Application
import android.content.Context
import com.example.paybacktask.presentation.fragments.DetailInfoFragment
import com.example.paybacktask.presentation.fragments.FirstFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [ApplicationModule::class, ViewModelModule::class]
)
interface ApplicationComponent {

    fun inject(fragment: FirstFragment)
    fun inject(fragment: DetailInfoFragment)

    @Component.Factory
    interface ComponentFactory {

        /** Метод для предоставления контекста */
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}