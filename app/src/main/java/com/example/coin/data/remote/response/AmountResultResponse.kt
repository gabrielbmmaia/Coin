package com.example.coin.data.remote.response

import com.example.coin.domain.AmountResult

/**
 * Essa é a classe que é utilizada na camada de data
 * e nela também precisa ter a função de conversão para
 * a classe que do domain
 * */

data class AmountResultResponse(
    val result: String
) {

    fun toAmountResult() =
        AmountResult(
            resultado = result
        )
}
