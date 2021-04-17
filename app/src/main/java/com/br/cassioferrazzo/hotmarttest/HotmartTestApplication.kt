package com.br.cassioferrazzo.hotmarttest

import android.app.Application
import com.br.cassioferrazzo.hotmarttest.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HotmartTestApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@HotmartTestApplication)
            modules(mainModule)
        }
    }
}