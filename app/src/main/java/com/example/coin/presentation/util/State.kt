package com.example.coin.presentation.util


/**
 * Essa classe deixa mais fácil controlar os estados
 * que a view pode ficar
 * */
sealed class State {
    class Success(val resultText: String) : State()
    class Failure(val errorText: String) : State()
    object Loading : State()
    object Empty : State()
}
