package com.pelletierantoine.mysephoratest.domain.common

abstract class SynchronousNoParamUseCase<out O> {

    protected abstract fun create(): O

    fun execute(): O = create()
}
