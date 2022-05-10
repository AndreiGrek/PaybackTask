package com.example.paybacktask.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.paybacktask.PixabyApplication
import com.example.paybacktask.R

class MainActivity : AppCompatActivity() {

    private val component by lazy {
        (application as PixabyApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)
    }
}