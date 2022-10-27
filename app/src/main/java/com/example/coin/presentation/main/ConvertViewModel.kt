package com.example.coin.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coin.core.Resource
import com.example.coin.domain.use_case.ConvertAmountUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.round

/**
 * Precisamos criar duas variáveis "Live Stream" que pode ser
 * um MutableLiveData/LiveData ou um MutableStateFlow/StateFlow.
 *
 * A variável Mutable precisa ser privada que é a variável onde
 * fazemos todas as modificações aqui dentro do viewModel e a
 * variável imutável precisa ser pública para a camada da View
 * poder OBSERVALA.
 * */

class ConvertViewModel(
    private val convertUseCases: ConvertAmountUseCase
) : ViewModel() {

    private val _convertionState = MutableStateFlow<ConvertState>(ConvertState.Empty)
    val convertionState: StateFlow<ConvertState> = _convertionState

    /**
     * Função que faz a conversão a partir da API.
     * */

    fun getConvertion(
        valor: String?,
        deMoedaBase: String,
        paraMoedaConvertida: String
    ) {
        /**
         * Aqui serve apenas para uma segurança caso o usuário
         * clique no botão de converter antes de digitar qualquer
         * número na caixa de texto.
         * */
        if (valor.isNullOrBlank()) {
            _convertionState.value = ConvertState.Error("Insira um valor")
            return
        }

        /**
         * Aqui é onde modificamos o estado da variável para que
         * a camada de view observe ela.
         * */

        viewModelScope.launch(IO) {

            convertUseCases(
                amount = valor,
                from = deMoedaBase,
                to = paraMoedaConvertida
            ).collect { result ->

                when (result) {

                    is Resource.Success -> {

                        /**
                         * Aqui estou pegando o resultado e arredondando para que ele
                         * fique com duas casas decimais.
                         * */

                        val resultado = result.data!!.resultado
                        val resultadoArredondado = round(resultado.toFloat() * 100) / 100

                        _convertionState.value =
                            ConvertState.Success(
                                "$valor $deMoedaBase = $resultadoArredondado $paraMoedaConvertida"
                            )
                    }

                    is Resource.Error -> {
                        _convertionState.value =
                            ConvertState.Error(
                                result.message ?: "Ocorreu um erro inesperado"
                            )
                    }

                    is Resource.Loading -> {
                        _convertionState.value = ConvertState.Loading
                    }
                }
            }
        }
    }
}
