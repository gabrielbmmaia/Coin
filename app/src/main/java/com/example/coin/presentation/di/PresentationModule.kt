package com.example.coin.presentation.di

import com.example.coin.presentation.main.ConvertViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module


/**
 * Organização de dependências da camada Presentation.
 * */

object PresentationModule {

    /**
     * Semelhante ao data di, aqui estamos agrupando em
     * uma única função todos os módulos que serão passados
     * para o App.
     * */

    fun load() {
        loadKoinModules(viewModelModule())
    }

    private fun viewModelModule(): Module {

        /**
         * Aqui estamos pegando a dependencia do UseCase
         * lá do Domain.di.
         * */

        return module {
            factory { ConvertViewModel(convertUseCases = get()) }
        }
    }
}