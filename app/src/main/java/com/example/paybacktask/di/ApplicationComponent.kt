package com.example.paybacktask.di

import com.example.paybacktask.presentation.activities.MainActivity
import com.example.paybacktask.presentation.fragments.FirstFragment
import dagger.Component

@Component(
    modules = [ApplicationModule::class]
)
interface ApplicationComponent {

    fun inject (activity: MainActivity)
    fun inject (fragment: FirstFragment)
}