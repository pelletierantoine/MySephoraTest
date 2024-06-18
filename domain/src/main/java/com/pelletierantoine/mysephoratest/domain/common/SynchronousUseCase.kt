package com.pelletierantoine.mysephoratest.domain.common

abstract class SynchronousUseCase<I, O> {

    protected abstract fun create(params: I): O

    fun execute(params: I): O = create(params)
}
