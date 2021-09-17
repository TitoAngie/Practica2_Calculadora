package com.example.calculadora2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var num1 = 0.0
    private var num2 = 0.0
    private var operacion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Resultado.text = "0"
        operacion = SIN_OPERACION

        UnoBtn.setOnClickListener { numberPressed("1") }
        DosBtn.setOnClickListener { numberPressed("2") }
        TresBtn.setOnClickListener { numberPressed("3") }
        CuatroBtn.setOnClickListener { numberPressed("4") }
        CincoBtn.setOnClickListener { numberPressed("5") }
        SeisBtn.setOnClickListener { numberPressed("6") }
        SieteBtn.setOnClickListener { numberPressed("7") }
        OchoBtn.setOnClickListener { numberPressed("8") }
        NueveBtn.setOnClickListener { numberPressed("9") }
        CeroBtn.setOnClickListener { numberPressed("0") }
        PuntoBtn.setOnClickListener { numberPressed(".") }

        BorrarBtn.setOnClickListener { resetAll() }

        SumBtn.setOnClickListener { operationPressed(SUMA) }
        RestBtn.setOnClickListener { operationPressed(RESTA) }
        MultBtn.setOnClickListener { operationPressed(MULTIPLICACION) }
        DivBtn.setOnClickListener { operationPressed(DIVISION) }

        ResBtn.setOnClickListener { resolvePressed() }
    }

    private fun numberPressed(num: String){
        if(Resultado.text == "0" && num != ".") {
            Resultado.text = "$num"
        } else {
            Resultado.text = "${Resultado.text}$num"
        }

        if(operacion == SIN_OPERACION){
            num1 = Resultado.text.toString().toDouble()
        } else {
            num2 = Resultado.text.toString().toDouble()
        }
    }

    private fun operationPressed(operacion: Int){
        this.operacion = operacion
        num1 = Resultado.text.toString().toDouble()

        Resultado.text = "0"
    }

    private fun resolvePressed(){

        val result = when(operacion) {
            SUMA -> num1 + num2
            RESTA -> num1 - num2
            MULTIPLICACION -> num1 * num2
            DIVISION -> num1 / num2
            else -> 0
        }

        num1 = result as Double

        Resultado.text = if("$result".endsWith(".0")) { "$result".replace(".0","") } else { "%.2f".format(result) }
    }

    private fun resetAll(){
        Resultado.text = "0"
        num1 = 0.0
        num2 = 0.0
    }

    companion object {
        const val SUMA = 1
        const val RESTA = 2
        const val MULTIPLICACION = 3
        const val DIVISION = 4
        const val SIN_OPERACION = 0
    }
}