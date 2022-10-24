package com.example.coin.data.remote.response

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    val name: String,
    @SerializedName("buy")
    val valor: String
)
