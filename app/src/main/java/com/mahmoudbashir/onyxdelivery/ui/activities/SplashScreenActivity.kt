package com.mahmoudbashir.onyxdelivery.ui.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mahmoudbashir.onyxdelivery.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashScreenActivity : AppCompatActivity() , CoroutineScope{
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main + Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // here we navigate to MainActivity after some seconds
        navigateToMainActivity()
    }

    private fun navigateToMainActivity() {

        launch {
            delay(2000)
            Intent(this@SplashScreenActivity,MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}