package com.example.calculatorr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun clrbtn(view: View) {
        tvInput?.text = ""
    }

    fun btnPoint(view: View) {
        if (lastNumeric && !lastDot) {
            tvInput?.append(".")
        }
        lastNumeric = false
        lastDot = true


        /*if(tvInput?.text?.contains(".") == false){
            tvInput?.append((view as Button).text)
        }
        else{
            return
        }*/

    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
            }
        }

    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("-") ||
                    value.contains("+") ||
                    value.contains("÷") ||
                    value.contains("×")

        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    var splitvalue = tvValue.split("-")
                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    one = prefix + one


                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }
                else if (tvValue.contains("+")){
                    var splitvalue = tvValue.split("+")
                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    one = prefix + one


                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }
                else if (tvValue.contains("÷")){
                    var splitvalue = tvValue.split("÷")
                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    one = prefix + one


                    tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }
                else if (tvValue.contains("×")){
                    var splitvalue = tvValue.split("×")
                    var one = splitvalue[0]
                    var two = splitvalue[1]

                    one = prefix + one


                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }


            } catch (e: Exception) {

            }


        }
    }

    private fun removeZeroAfterDot(result: String) : String{
        var value = result
        if(value.contains(".0"))
            value = value.substring(0,value.length-2)

        return value

    }
}