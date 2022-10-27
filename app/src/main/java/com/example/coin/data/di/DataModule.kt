package com.example.coin.data.di

import android.util.Log
import com.example.coin.data.respository.CurrencyRepositoryImpl
import com.example.coin.data.remote.services.CurrencyServices
import com.example.coin.domain.CurrencyRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 *  Organização de dependências da camada Data
 * */

object DataModule {

    /**
     * Aqui temos as constantes utilizadas na criação do service
     * */

    private const val BASE_URL = "https://api.apilayer.com/exchangerates_data/"
    private const val OK_HTTP = "ok http"

    /**
     *  --> FUNÇÃO MUITO IMPORTANTE NO QUESITO ORGANIZAÇÃO <--
     *  A função load serve para carregar todos os módulos
     *  dessa camada. Essa função é chamda pela classe App
     *  quando o app é inicializado. É ideal existir essa
     *  função para organizar aqui todos os módulos que
     *  serão enviados para a classe App
     * */

    fun load() {
        loadKoinModules(currencyModule() + networkModule())
    }

    private fun currencyModule(): Module {
        return module {
            single<CurrencyRepository> { CurrencyRepositoryImpl(service = get()) }
        }
    }

    private fun networkModule(): Module {
        return module {

            /**
             * Aqui estamos criando o CurrencyService e
             * colocando como parâmetro os singleton abaixo
             * */

            single <CurrencyServices> { createService(factory = get(), client = get()) }

            /**
             * Aqui estamos mostrando como criamos o interceptor OkHttp pro Koin
             * */

            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            /**
             * Aqui mostramos pro Koin como se cria a factory com o Moshi
             * */

            single {
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            }
        }
    }

    /**
     * Colocamos os generecis aqui nessa criação de servico Api
     * para poder reutilizar posteriomente em outros serviços
     * e para isso só é necessário indicar qual é o serviço
     * depois do "single" dentro do modulo do Koin.
     * A expressão "<reified T> significa que redefinimos o "T"
     * dentro da função pelo generic que for indicado quando a função é chamada.
     * */

    private inline fun <reified T> createService(
        factory: Moshi,
        client: OkHttpClient
    ): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // Base Url da API
            .addConverterFactory(MoshiConverterFactory.create(factory)) // Factory de conversão JSON
            .client(client) // Interceptor OkHttp
            .build() // Criação do Retrofit
            .create(T::class.java) // Criação do serviço
    }
}