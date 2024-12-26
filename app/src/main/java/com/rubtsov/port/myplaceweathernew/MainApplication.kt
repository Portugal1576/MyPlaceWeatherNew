package com.rubtsov.port.myplaceweathernew

import android.app.Application
import com.rubtsov.port.myplaceweathernew.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
            // Передаємо властивості, такі як API ключ
            properties(
                mapOf(
                    "OPENWEATHERMAP_API_KEY" to BuildConfig.OPENWEATHERMAP_API_KEY
                )
            )
        }
    }
}
