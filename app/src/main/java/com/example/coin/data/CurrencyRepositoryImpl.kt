package com.example.coin.data

import com.example.coin.data.remote.services.CurrencyServices
import com.example.coin.domain.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.internal.filterList

class CurrencyRepositoryImpl(private val service: CurrencyServices) : CurrencyRepository {

    override suspend fun converteValorUsd(valorReal: String): Flow<String> {
        TODO("Not yet implemented")
    }


override suspend fun converteValorEur(valorReal: String): Flow<String> {
    TODO("Not yet implemented")
}


}