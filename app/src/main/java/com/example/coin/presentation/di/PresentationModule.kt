package com.example.coin.presentation.di

import com.example.coin.presentation.main.MainViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module


/**
 * Organização de dependências da camada Presentation
 * */

object PresentationModule {

    /**
     * Semelhante ao data di, aqui estamos agrupando em
     * uma função só todos módulos que serão passados
     * para o App
     * */

    fun load() {
        loadKoinModules(viewModelModule())
    }

    private fun viewModelModule(): Module {

        /**
         * Aqui estamos pegando a dependencia do repository
         * lá do data.di
         * */
        return module {
            factory { MainViewModel(repository = get()) }
        }

    }
}