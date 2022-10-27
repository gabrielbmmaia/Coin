package com.example.coin.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coin.data.util.Resource
import com.example.coin.domain.CurrencyRepository
import com.example.coin.presentation.util.State
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.round

/**
 * No ViewModel precisamos indicar que ela é um ViewModel().
 * Precisamos indicar suas dependências como todas as outras
 * classes a partir do seu construtor.
 * Precisamos criar duas variáveis "Live Stream" que pode ser
 * um MutableLiveData/LiveData ou um MutableStateFlow/StateFlow.
 * A variável Mutable precisa ser privada que é a variável onde
 * fazemos todas as modificações aqui dentro do viewModel e a
 * variável imutável precisa ser pública para a camada da View
 * poder OBSERVALA. A variável imutável é um "cast" da variável mutável.
 * */

class MainViewModel(private val repository: CurrencyRepository) : ViewModel() {

    /**
     * Variáveis sitadas anteriormente
     * */
    private val _valorConvertido = MutableStateFlow<State>(State.Empty)
    val valorconvertido: StateFlow<State> = _valorConvertido

    /**
     * Função que faz a conversão a partir da API
     * */
    fun converterValor(
        valor: String?,
        deMoedaBase: String,
        paraMoedaConvertida: String
    ) {
        /**
         * Aqui serve apenas para uma segurança caso o usuário
         * clique no botão de converter antes de digitar qualquer
         * número na caixa de texto
         * */
        if (valor == null) {
            _valorConvertido.value = State.Failure("Insira um valor")
            return
        }

        /**
         * Aqui é onde modificamos o estado da variável mutável para que
         * a camada de view observe ela
         * */
        viewModelScope.launch(IO) {
            _valorConvertido.value = State.Loading
            when (val valorResponse = repository.convertAmount(
                amount = valor,
                from = deMoedaBase,
                to = paraMoedaConvertida
            )) {
                is Resource.Error -> {
                    _valorConvertido.value =
                        State.Failure(valorResponse.message!!)
                }

                is Resource.Success -> {
                    val resultado = valorResponse.data!!.resultado
                    val resultadoArredondado = round(resultado.toFloat() * 100) / 100

                    _valorConvertido.value = State.Success(
                        "$valor $deMoedaBase = $resultadoArredondado $paraMoedaConvertida"
                    )
                } // final Success
            } // final do when
        } // final viewModelScope
    } // final função
} // final viewModel