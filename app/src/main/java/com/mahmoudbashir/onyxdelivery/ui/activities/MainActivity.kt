package com.mahmoudbashir.onyxdelivery.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mahmoudbashir.onyxdelivery.R
import com.mahmoudbashir.onyxdelivery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

    }
}