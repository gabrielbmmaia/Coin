package com.example.coin.domain

import com.example.coin.data.util.Resource


interface CurrencyRepository {

    suspend fun convertAmount(to: String, from: String, amount: String): Resource<AmountResult>

}