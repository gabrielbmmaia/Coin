package com.example.coin.data.util


/**
 * Essa é uma classe genérica que serve de suporte para deixar
 * o tratemento de erros mais fácil
 * */
sealed class Resource<T>(val data: T?, val message: String?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: String) : Resource<T>(null, message)
}