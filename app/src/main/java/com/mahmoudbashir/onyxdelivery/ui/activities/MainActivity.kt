package com.mahmoudbashir.onyxdelivery.ui.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.mahmoudbashir.onyxdelivery.R
import com.mahmoudbashir.onyxdelivery.databinding.ActivityMainBinding
import com.mahmoudbashir.onyxdelivery.viewModel.LoginViewModel
import com.mahmoudbashir.onyxdelivery.viewModel.OrdersViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this,R.color.inputsColor)

        // this is a depracated but i've used it for make all screen to be full under status bar ...
        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }
}