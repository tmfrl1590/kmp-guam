package com.party.guam

import android.app.Application
import com.party.guam.di.initKoin
import org.koin.android.ext.koin.androidContext

class GuamApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@GuamApplication)
        }
    }
}