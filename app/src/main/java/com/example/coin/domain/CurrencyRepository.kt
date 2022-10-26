package com.example.coin.domain

import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    suspend fun convertAmount(to: String, from: String, amount: String): AmountResult
}