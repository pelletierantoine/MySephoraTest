package com.pelletierantoine.mysephoratest

import android.app.Application
import com.pelletierantoine.mysephoratest.data.dataModule
import com.pelletierantoine.mysephoratest.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Sephora : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@Sephora)
            modules(dataModule)
            modules(domainModule)
        }
    }
}