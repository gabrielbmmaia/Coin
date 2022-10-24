package com.example.coin.data.entities

import com.example.coin.domain.Currency
import com.squareup.moshi.Json

data class CurrencyEntity(
    val name: String,
    @Json(name = "buy")
    val valor: String
){

    fun toCurrency() : Currency {
        return Currency(
            nome = name,
            valor = valor
        )
    }
}
