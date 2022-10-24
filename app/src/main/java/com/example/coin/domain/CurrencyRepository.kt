package com.example.coin.domain

interface CurrencyRepository {

    fun converteValorUsd(valorReal: String) : String

    fun converteValorEur(valorReal: String) : String

}