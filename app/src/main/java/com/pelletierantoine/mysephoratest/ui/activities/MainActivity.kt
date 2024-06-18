package com.pelletierantoine.mysephoratest.ui.activities

import android.view.LayoutInflater
import com.pelletierantoine.mysephoratest.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.pelletierantoine.mysephoratest.databinding.ActivityMainBinding as Binding
import com.pelletierantoine.mysephoratest.ui.activities.MainViewModel as ViewModel

class MainActivity : BaseActivity<Binding, MainState, ViewModel>() {

    override val viewModel: ViewModel by viewModel()
    override fun initViewBinding(inflater: LayoutInflater): Binding = Binding.inflate(inflater)

    override fun initViews() {

    }

    override fun render(state: MainState) {

    }
}