package com.example.coin.presentation.main

/**
 * Essa classe deixa mais fácil controlar os estados
 * que a view pode ficar.
 * */

sealed class ConvertState {
    object Loading : ConvertState()
    object Empty : ConvertState()
    class Success(val result: String) : ConvertState()
    class Error(val errorMessage: String) : ConvertState()
}


