package com.example.coin.data

import com.example.coin.data.remote.services.CurrencyServices
import com.example.coin.data.util.Resource
import com.example.coin.domain.AmountResult
import com.example.coin.domain.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.internal.filterList


/**
 * Essa classe serve como a implementação da interface CurrencyRepository.
 * Quando a camada de ViewModel pede por um "CurrencyRepository" como dependência
 * é essa classe que ela vai receber para poder manipular por conta da Injeção de
 * Depedência do Koin.
 * Nós precisamos fazer com que as camdas sejam dependentes de interfaces por conta
 * do SOLID Principles falado no Clean Architecture. Quando as classes são dependentes
 * de interfaces e essas interfaces tem implementações que são injetadas a partir da injeção
 * de depedência, o código fica mais fácil de melhorar e dar manutenções já que a maioria
 * das mudanças serão feitas apenas nas implementações dessas interfaces enquanto as outras
 * camadas não são afetadas.
 * */
class CurrencyRepositoryImpl(private val service: CurrencyServices) : CurrencyRepository {

    override suspend fun convertAmount(
        to: String,
        from: String,
        amount: String
    ): Resource<AmountResult> {
        return try {
            val response = service.getCurrenciesResults(to = to, from = from, amount = amount)
            val result = response.body()

            if (response.isSuccessful && result != null) {
                Resource.Success(result.toAmountResult())
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Aconteceu um erro")
        }
    }
}