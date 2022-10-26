package com.example.coin.data.remote.services

import com.example.coin.data.remote.response.AmountResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CurrencyServices {

    /**
     * Serviço da api de converter o valor
     * */

    @GET("convert")
    suspend fun getCurrenciesResults(
        @Header("apikey") key: String = API_KEY,
        @Query("to") to: String,
        @Query("from") from: String,
        @Query("amount") amount: String
    ): Response<AmountResultResponse>


    companion object {
        const val API_KEY = "C8FQrTQ1h12L99UCY8MUcOzfZ18PS8ct"
    }
}
