package com.example.paybacktask.di

import android.content.Context
import com.example.paybacktask.presentation.fragments.FirstFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [ApplicationModule::class, ViewModelModule::class]
)
interface ApplicationComponent {

    fun inject(fragment: FirstFragment)

    @Component.Factory
    interface ComponentFactory {

        /** Метод для предоставления контекста */
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}