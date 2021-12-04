package com.example.calcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    lateinit var edit :EditText
    lateinit var text:TextView
    var result:Double = 0.0
    var isOperatorKeyPushed : Boolean = false
    var recentOperator = R.id.btn_equal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit = findViewById(R.id.edit)
        text = findViewById(R.id.textView)

        val list = (0..9).toList()
        for(i in list){
            val num = resources.getIdentifier("btn${i}","id",packageName)
            findViewById<Button>(num).setOnClickListener(Numberlistner)
        }
        findViewById<Button>(R.id.btn_equal).setOnClickListener(OperatorListner)
        findViewById<Button>(R.id.btn_decimal).setOnClickListener(Numberlistner)
        findViewById<Button>(R.id.btn_plus).setOnClickListener(OperatorListner)
        findViewById<Button>(R.id.btn_sub).setOnClickListener(OperatorListner)
        findViewById<Button>(R.id.btn_mul).setOnClickListener(OperatorListner)
        findViewById<Button>(R.id.btn_div).setOnClickListener(OperatorListner)
        findViewById<Button>(R.id.clear).setOnClickListener(ClearListner)
    }
    val Numberlistner = object:View.OnClickListener{
        override fun onClick(v: View?) {
            val btn = v as? Button
            if(isOperatorKeyPushed == true){
                edit.setText(btn?.text)
            }else{
                edit.append(btn?.text)
            }
            isOperatorKeyPushed = false
        }
    }
    val OperatorListner = object:View.OnClickListener{
        override fun onClick(v: View?) {
            val OperatorButton = v as? Button
            val value:Double = edit.text.toString().toDouble()
            if(recentOperator == R.id.btn_equal){
                result = value
            }else{
                result = calc(recentOperator,result,value)
                edit.setText(result.toString())
            }
            recentOperator = OperatorButton!!.id
            text.setText(OperatorButton.text)
            isOperatorKeyPushed = true
        }
    }
    val ClearListner = object:View.OnClickListener{
        override fun onClick(v: View?) {
            recentOperator = R.id.btn_equal
            result = 0.0
            isOperatorKeyPushed = false
            text.setText("")
            edit.setText("")
        }
    }
    fun calc(operator:Int,value1:Double,value2:Double):Double{
        when(operator){
            R.id.btn_plus -> return value1 + value2
            R.id.btn_sub -> return value1 - value2
            R.id.btn_mul -> return value1 * value2
            R.id.btn_div -> return value1 / value2
            else -> return value1
        }
    }
}