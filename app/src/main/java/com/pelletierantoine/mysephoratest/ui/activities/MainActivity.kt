package com.pelletierantoine.mysephoratest.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.pelletierantoine.mysephoratest.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.pelletierantoine.mysephoratest.databinding.ActivityMainBinding as Binding
import com.pelletierantoine.mysephoratest.ui.activities.MainViewModel as ViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: ViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Binding.inflate(layoutInflater)

        installSplashScreen().apply {
            setKeepVisibleCondition {
                viewModel.isLoading.value
            }
        }

        setContentView(binding.root)
    }
}