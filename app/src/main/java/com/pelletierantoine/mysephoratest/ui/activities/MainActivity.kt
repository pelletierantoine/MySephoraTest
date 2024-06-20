package com.pelletierantoine.mysephoratest.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.pelletierantoine.mysephoratest.databinding.ActivityMainBinding
import com.pelletierantoine.mysephoratest.databinding.ActivityMainBinding as Binding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Binding.inflate(layoutInflater)

        installSplashScreen()

        setContentView(binding.root)
    }
}