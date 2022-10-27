package com.example.coin.data.remote.response

import com.example.coin.domain.model.AmountResult

/**
 * Essa é a classe que é utilizada na camada de data.
 *
 * É necessário ter a função de conversão para
 * a classe do domain que faz conexão com as camadas do
 * presentation.
 *
 * É necessario dividir entre duas classes ( uma que é
 * usada na camada de data e outro que é usada na camada do
 * domain & presentation ) porque qualquer mudança de nome das
 * variáveis que vem da api, ou mudança de api ou mudança do
 * tipo de variável que a api envia essa alterações podem ser
 * facilmente tratadas na conversão dessa classe para a classe
 * de domain.
 * */

data class AmountResultResponse(
    val result: String
) {

    fun toAmountResult() =
        AmountResult(
            resultado = result
        )
}
