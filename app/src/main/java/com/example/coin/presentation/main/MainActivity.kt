package com.example.coin.presentation.main

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.coin.databinding.ActivityMainBinding
import com.example.coin.presentation.util.State
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A camada de View deve saber como a view se
 * porta diante de cada estado enviado pela
 * ViewModel através da variável que a View
 * está observando
 * */
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        enviarValores()
        receberValores()
    }

    /**
     * Essa função serve para a view se comporte diferente
     * de acordo com cada estado possível que a ViewModel
     * pode enviar para ela
     * */

    private fun receberValores() {
        lifecycleScope.launchWhenStarted {
            viewModel.valorconvertido.collect { state ->
                when (state) {
                    is State.Success -> {
                        binding.mainActivityProgressBar.isVisible = false
                        binding.mainActivityResultado.isVisible = true
                        binding.mainActivityResultado.setTextColor(Color.BLACK)
                        binding.mainActivityResultado.text = state.resultText
                    }
                    is State.Failure -> {
                        binding.mainActivityProgressBar.isVisible = false
                        binding.mainActivityResultado.isVisible = true
                        binding.mainActivityResultado.setTextColor(Color.RED)
                        binding.mainActivityResultado.text = state.errorText
                    }
                    is State.Loading -> {
                        binding.mainActivityProgressBar.isVisible = true
                        binding.mainActivityResultado.isVisible = false
                    }
                    else -> {}
                }
            }
        }
    }

    /**
     * Função serve para enviar os dados necessários para que a ViewModel
     * */

    private fun enviarValores() {
        binding.mainActivityButtonConverter.setOnClickListener {
            viewModel.converterValor(
                valor = binding.mainActivityEditTextInputValor.text.toString(),
                deMoedaBase = binding.mainActivitySpinnerFrom.selectedItem.toString(),
                paraMoedaConvertida = binding.mainActivitySpinnerTo.selectedItem.toString()
            )
        }
    }
}