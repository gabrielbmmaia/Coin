package com.example.coin.data

import com.example.coin.data.remote.services.CurrencyServices
import com.example.coin.data.util.Resource
import com.example.coin.domain.AmountResult
import com.example.coin.domain.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.internal.filterList

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
            Resource.Error(e.message ?: "Aconteceu um error")
        }
    }
}