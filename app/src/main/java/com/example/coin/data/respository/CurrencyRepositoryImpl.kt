package com.example.coin.data.respository

import com.example.coin.data.remote.services.CurrencyServices
import com.example.coin.domain.model.AmountResult
import com.example.coin.domain.repository.CurrencyRepository


/**
 * Essa classe serve como a implementação da interface CurrencyRepository.
 * Quando a camada de Domain pede por um "CurrencyRepository" como dependência
 * é essa classe que ela vai receber para poder manipular por conta da Injeção de
 * Depedência do Koin.
 *
 * Nós precisamos fazer com que as camdas sejam dependentes de interfaces por conta
 * do SOLID Principles falado no Clean Architecture. Quando as classes são dependentes
 * de interfaces e essas interfaces tem implementações que são injetadas a partir da injeção
 * de depedência, o código fica mais fácil para melhorar e dar manutenções já que a maioria
 * das mudanças serão feitas apenas nas implementações dessas interfaces enquanto as outras
 * camadas não são afetadas.
 *
 * O Repository em si serve para enviar os dados que a camade de Domain precisa. O Domain
 * não quer saber como o repository achou os dados (Api, Dao, file, etc), ela só quer o
 * resultado. Aqui nessa classe de "intermediação" que fazemos a relação da API com o DAO
 * caso necessário.
 * */

class CurrencyRepositoryImpl(private val service: CurrencyServices) : CurrencyRepository {

    override suspend fun convertAmount(
        to: String,
        from: String,
        amount: String
    ): AmountResult {
        return service.getCurrenciesResults(
            to = to,
            from = from,
            amount = amount
        ).toAmountResult()
    }
}