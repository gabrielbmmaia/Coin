package com.example.coin.domain.repository

import com.example.coin.domain.model.AmountResult


interface CurrencyRepository {

    suspend fun convertAmount(to: String, from: String, amount: String): AmountResult

}