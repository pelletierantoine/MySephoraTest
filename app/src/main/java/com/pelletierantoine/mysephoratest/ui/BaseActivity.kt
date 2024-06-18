package com.pelletierantoine.mysephoratest.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding, S : IState, VM : BaseViewModel<S>> : AppCompatActivity() {

    protected lateinit var binding: B
    abstract val viewModel: VM

    protected abstract fun initViewBinding(inflater: LayoutInflater): B

    protected abstract fun render(state: S)

    abstract fun initViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initViewBinding(layoutInflater).apply {
            viewModel.subscribe(this@BaseActivity, this@BaseActivity::render)
        }
        setContentView(binding.root)

        initViews()
    }
}