package com.example.coin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application () {

    override fun onCreate() {
        super.onCreate()

        /**
         * Iniciando o Koin no aplicativo
         * */

        startKoin {
            androidContext(this@App)
        }

        /**
         * Carregar os módulos
         * */
    }
}