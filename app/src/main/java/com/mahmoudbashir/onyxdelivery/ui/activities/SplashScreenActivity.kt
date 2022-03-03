package com.mahmoudbashir.onyxdelivery.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mahmoudbashir.onyxdelivery.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // here we navigate to MainActivity after some seconds
        navigateToMainActivity()
    }

    private fun navigateToMainActivity() {
        GlobalScope.launch {
            delay(2000)
            Intent(this@SplashScreenActivity,MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}