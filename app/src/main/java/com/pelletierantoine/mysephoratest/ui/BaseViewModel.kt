package com.pelletierantoine.mysephoratest.ui

import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<S : IState> : ViewModel(), KoinComponent {

    private val state: MutableLiveData<S> by lazy { MutableLiveData() }

    @CallSuper
    open fun dispatch(state: S) {
        this.state.value = state
    }

    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job = viewModelScope.launch(
        context = context,
        start = start,
        block = block
    )

    fun subscribe(lifecycle: LifecycleOwner, observer: (S) -> Unit) {
        state.observe(lifecycle) {
            it?.let(observer)
        }
    }

    fun unsubscribe(lifecycle: LifecycleOwner) {
        state.removeObservers(lifecycle)
    }
}
