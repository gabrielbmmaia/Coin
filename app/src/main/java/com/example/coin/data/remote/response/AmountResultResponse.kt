package com.example.coin.data.remote.response

import com.example.coin.domain.AmountResult

data class AmountResultResponse(
    val result: String
) {

    fun toAmountResult() =
        AmountResult(
            resultado = result
        )
}
