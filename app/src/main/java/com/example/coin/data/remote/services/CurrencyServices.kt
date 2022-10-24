package com.example.coin.data.remote.services

import com.example.coin.data.remote.response.ResultResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyServices {

    /**
     * Serviço para pegar todas as currencies da Api
     * */

    @GET("finance")
    suspend fun getCurrenciesResults(
        @Query("key") key: String = API_KEY
    ): Call<ResultResponse>

    companion object {
        val API_KEY = "24c44c6c"
    }

}