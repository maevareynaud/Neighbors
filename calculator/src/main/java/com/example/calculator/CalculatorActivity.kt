package com.example.calculator

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import com.example.calculator.databinding.CalculatorActivityBinding

class CalculatorActivity : AppCompatActivity() {

    lateinit var binding: CalculatorActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.calculator_activity)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        compute()
    }

    private fun sum() {
        with(binding) {
            val operandFirst = operatorFirst.toInt()
            val operandSecond = operatorSecond.toInt()

            computeResult.text = "${ operandFirst.plus(operandSecond) }"
        }
    }

    private fun enableButton() {
        with(binding){
            computeButton.isEnabled = !operatorFirst.text.isNullOrEmpty() && !operatorSecond.text.isNullOrEmpty()
        }
    }

    private fun compute() {

        // le  with(binding){} permet de ne pas avoir a ecrire à chaque fois binding.computeButton, binding.operatorFirst etc...
        with(binding) {
            computeButton.setOnClickListener {
                sum()
            }

            operatorFirst.doAfterTextChanged {
                enableButton()
                sum()
            }

            operatorSecond.doAfterTextChanged {
                enableButton()
                sum()
            }
        }
    }

    /*
    * Fonction d'extension
    *  1) Récupère le text d'un champ
    *  2) le converti en string puis en int
    */

    // Pour éviter que ça crash, si c'est pas un int on renvoi null --> pas de crash
    private fun EditText.toInt(): Int = text.toString().toIntOrNull() ?: 0
}
