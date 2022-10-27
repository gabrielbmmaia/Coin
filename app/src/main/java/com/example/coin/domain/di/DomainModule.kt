package com.example.coin.domain.di

import com.example.coin.domain.use_case.ConvertAmountUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 *  Organização de dependências da camada Domain.
 * */

object DomainModule {

    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory { ConvertAmountUseCase(repository = get()) }
        }
    }


}