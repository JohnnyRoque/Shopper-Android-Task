package com.iceman.shopperdrive.application

import android.app.Application
import com.iceman.data.di.dataModule
import com.iceman.domain.model.di.domainModule
import com.iceman.home.di.homeScreenModule
import com.iceman.routedetails.di.routeDetailScreenModule
import com.iceman.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SdApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SdApplication)
            modules(
                dataModule,
                domainModule,
                homeScreenModule,
                routeDetailScreenModule,
                uiModule,
                appModule
            )
        }
    }
}