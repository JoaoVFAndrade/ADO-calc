package com.example.adocalculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.adocalculadora.R

class MainActivity : AppCompatActivity() {
    private lateinit var primeiroNumero: EditText
    private lateinit var segundoNumero: EditText
    private lateinit var textViewResult: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var mensagem: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        primeiroNumero = findViewById(R.id.primeiro_numero)
        segundoNumero = findViewById(R.id.segundo_numero)
        textViewResult = findViewById(R.id.textViewResult)
        radioGroup = findViewById(R.id.radioGroup)


    }

    fun numero(num1Str: String, num2Str: String): Pair<Double, Double>? {
        val num1 = num1Str.toDoubleOrNull()
        val num2 = num2Str.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            textViewResult.text = "Erro: Números inválidos"
            return null
        }

        return Pair(num1, num2)
    }


    fun calcularOperacao(view: View) {

        val numeros =
            numero(primeiroNumero.text.toString(), segundoNumero.text.toString()) ?: return

        val num1 = numeros.first
        val num2 = numeros.second


        val opcao = radioGroup.checkedRadioButtonId


        val resultado: Double = when {
            opcao == R.id.adicao -> num1 + num2
            opcao == R.id.subtracao -> num1 - num2
            opcao == R.id.multiplicacao -> num1 * num2
            opcao == R.id.divisao -> {
                if (num2 != 0.0) {
                    num1 / num2
                } else {

                    textViewResult.text = "Erro: Divisão por zero!"
                    return
                }
            }

            else -> {
                textViewResult.text = "Erro: Nenhuma operação selecionada"
                return
            }
        }

        textViewResult.text = "Resultado: $resultado"
    }

    fun resetarValores(view: View) {

        primeiroNumero.text.clear()
        segundoNumero.text.clear()

        radioGroup.clearCheck()

        textViewResult.text = "Resultado: "
    }

}