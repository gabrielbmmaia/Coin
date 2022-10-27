package com.example.coin.domain

import com.example.coin.data.util.Resource
import com.example.coin.domain.model.AmountResult


interface CurrencyRepository {

    suspend fun convertAmount(to: String, from: String, amount: String): Resource<AmountResult>

}