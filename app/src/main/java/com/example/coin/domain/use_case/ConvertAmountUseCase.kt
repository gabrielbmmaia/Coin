package com.example.coin.domain.use_case

import com.example.coin.core.Resource
import com.example.coin.domain.model.AmountResult
import com.example.coin.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * O use_case serve para fazer a comunicação entre a camada de data e
 * os ViewModels. Aqui fazemos a logica com os dados que são enviados
 * pelo repository e emitimos o resultado para a camada de ViewModel
 * através de Resources para deixa mais facil de tratar os resultados
 * na viewmodel.
 * */

class ConvertAmountUseCase(
    private val repository: CurrencyRepository
) {

    operator fun invoke(
        to: String,
        from: String,
        amount: String
    ): Flow<Resource<AmountResult>> = flow {
        try {
            emit(Resource.Loading())

            val result = repository.convertAmount(to = to, from = from, amount = amount)

            emit(Resource.Success(result))

        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "Aconteceu um erro"))

        }
    }
}