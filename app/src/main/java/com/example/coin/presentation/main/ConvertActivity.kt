package com.example.coin.presentation.main

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.coin.databinding.ActivityConvertBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A camada de View deve saber como a view se
 * porta diante de cada estado enviado pela
 * ViewModel através da variável que a View
 * está observando.
 * */
class ConvertActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityConvertBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModel<ConvertViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        enviarValores()
        receberValores()
    }

    /**
     * Essa função serve para que a view se comporte de
     * acordo com cada estado possível que a ViewModel
     * está emitindo para ela.
     * */

    private fun receberValores() {
        lifecycleScope.launchWhenStarted {
            viewModel.convertionState.collect { state ->

                when (state) {

                    is ConvertState.Success -> {
                        binding.mainActivityProgressBar.isVisible = false
                        binding.mainActivityResultado.isVisible = true
                        binding.mainActivityResultado.setTextColor(Color.BLACK)
                        binding.mainActivityResultado.text = state.result
                    }

                    is ConvertState.Error -> {
                        binding.mainActivityProgressBar.isVisible = false
                        binding.mainActivityResultado.isVisible = true
                        binding.mainActivityResultado.setTextColor(Color.RED)
                        binding.mainActivityResultado.text = state.errorMessage
                    }

                    is ConvertState.Loading -> {
                        binding.mainActivityProgressBar.isVisible = true
                        binding.mainActivityResultado.isVisible = false
                    }

                    else -> {}

                }
            }
        }
    }

    /**
     * Função serve para enviar os dados necessários para que a ViewModel.
     * */

    private fun enviarValores() {
        binding.mainActivityButtonConverter.setOnClickListener {
            viewModel.getConvertion(
                valor = binding.mainActivityEditTextInputValor.text.toString(),
                deMoedaBase = binding.mainActivitySpinnerFrom.selectedItem.toString(),
                paraMoedaConvertida = binding.mainActivitySpinnerTo.selectedItem.toString()
            )
        }
    }
}