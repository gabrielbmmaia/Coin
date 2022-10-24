package com.example.coin.data

import com.example.coin.data.remote.services.CurrencyServices
import com.example.coin.domain.CurrencyRepository

class CurrencyRepositoryImpl (private val service: CurrencyServices) : CurrencyRepository {

    override fun converteValorUsd(valorReal: String): String {
        TODO("Not yet implemented")
    }

    override fun converteValorEur(valorReal: String): String {
        TODO("Not yet implemented")
    }
}