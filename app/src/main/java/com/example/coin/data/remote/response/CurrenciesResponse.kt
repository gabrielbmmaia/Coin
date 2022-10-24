package com.example.coin.data.remote.response

import com.example.coin.data.entities.CurrencyEntity

data class CurrenciesResponse(
    val currencies: List<CurrencyEntity>
)
