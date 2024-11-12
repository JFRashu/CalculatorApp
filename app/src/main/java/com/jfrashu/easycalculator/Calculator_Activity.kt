package com.jfrashu.easycalculator

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import net.objecthunter.exp4j.ExpressionBuilder

class Calculator_Activity : AppCompatActivity(), View.OnClickListener {
    private lateinit var expressionTextView: TextView
    private lateinit var resultTextView: TextView

    private var currentExpression: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        expressionTextView = findViewById(R.id.calculation)
        resultTextView = findViewById(R.id.result)

        findViewById<MaterialButton>(R.id.button_0).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_1).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_2).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_3).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_4).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_5).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_6).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_7).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_8).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_9).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_point).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_plus).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_minus).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_muliply).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_divide).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_percent).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_square).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_root).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_ac).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_clear).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_lb).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_rb).setOnClickListener(this)
        findViewById<MaterialButton>(R.id.button_equal).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_0 -> appendToExpression("0")
            R.id.button_1 -> appendToExpression("1")
            R.id.button_2 -> appendToExpression("2")
            R.id.button_3 -> appendToExpression("3")
            R.id.button_4 -> appendToExpression("4")
            R.id.button_5 -> appendToExpression("5")
            R.id.button_6 -> appendToExpression("6")
            R.id.button_7 -> appendToExpression("7")
            R.id.button_8 -> appendToExpression("8")
            R.id.button_9 -> appendToExpression("9")
            R.id.button_point -> appendToExpression(".")
            R.id.button_plus -> appendToExpression("+")
            R.id.button_minus -> appendToExpression("-")
            R.id.button_muliply -> appendToExpression("*")
            R.id.button_divide -> appendToExpression("/")
            R.id.button_percent -> appendToExpression("%")
            R.id.button_square -> appendToExpression("^2")
            R.id.button_root -> appendToExpression("√(")
            R.id.button_lb -> appendToExpression("(")
            R.id.button_rb -> appendToExpression(")")
            R.id.button_ac -> clearExpression()
            R.id.button_clear -> deleteLastCharacter()
            R.id.button_equal -> evaluateExpression()
        }
    }

    private fun appendToExpression(character: String) {
        currentExpression += character
        expressionTextView.text = currentExpression
    }

    private fun clearExpression() {
        currentExpression = ""
        expressionTextView.text = ""
        resultTextView.text = ""
    }

    private fun deleteLastCharacter() {
        if (currentExpression.isNotEmpty()) {
            currentExpression = currentExpression.substring(0, currentExpression.length - 1)
            expressionTextView.text = currentExpression
        }
    }

    private fun evaluateExpression() {
        try {
            val expression = ExpressionBuilder(currentExpression).build()
            val result = expression.evaluate()
            resultTextView.text = result.toString()
        } catch (e: Exception) {
            resultTextView.text = "Error"
        }
    }
}