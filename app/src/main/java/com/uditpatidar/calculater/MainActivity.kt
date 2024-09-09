package com.uditpatidar.calculater

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import net.objecthunter.exp4j.ExpressionBuilder

import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView

import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var tvOne: TextView
    private lateinit var tvTwo: TextView
    private lateinit var tvThree: TextView
    private lateinit var tvFour: TextView
    private lateinit var tvFive: TextView
    private lateinit var tvSix: TextView
    private lateinit var tvSeven: TextView
    private lateinit var tvEight: TextView
    private lateinit var tvNine: TextView
    private lateinit var tvZero: TextView
    private lateinit var tvDot: TextView
    private lateinit var tvPlus: TextView
    private lateinit var tvMinus: TextView
    private lateinit var tvMultiply: TextView
    private lateinit var tvDivide: TextView
    private lateinit var tvOpen: TextView
    private lateinit var tvClose: TextView
    private lateinit var tvClear: TextView
    private lateinit var tvBack: ImageView
    private lateinit var tvEqual: TextView
    private lateinit var tvExpression: TextView
    private lateinit var tvResult: TextView
    private lateinit var tvWelcome: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        //Hide Status bar
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvWelcome = findViewById(R.id.tvwelcome)
        tvOne = findViewById(R.id.tvOne)
        tvTwo = findViewById(R.id.tvTwo)
        tvThree = findViewById(R.id.tvThree)
        tvFour = findViewById(R.id.tvFour)
        tvFive = findViewById(R.id.tvFive)
        tvSix = findViewById(R.id.tvSix)
        tvSeven = findViewById(R.id.tvSeven)
        tvEight = findViewById(R.id.tvEight)
        tvNine = findViewById(R.id.tvNine)
        tvZero = findViewById(R.id.tvZero)
        tvDot = findViewById(R.id.tvDot)
        tvPlus = findViewById(R.id.tvPlus)
        tvMinus = findViewById(R.id.tvMinus)
        tvMultiply = findViewById(R.id.tvMultiply)
        tvDivide = findViewById(R.id.tvDivide)
        tvOpen = findViewById(R.id.tvOpen)
        tvClose = findViewById(R.id.tvClose)
        tvClear = findViewById(R.id.tvClear)
        tvBack = findViewById(R.id.tvBack)
        tvEqual = findViewById(R.id.tvEqual)
        tvExpression = findViewById(R.id.tvExpression)
        tvResult = findViewById(R.id.tvResult)

        // Number buttons

        tvOne.setOnClickListener { appendOnExpression("1", true) }
        tvTwo.setOnClickListener { appendOnExpression("2", true) }
        tvThree.setOnClickListener { appendOnExpression("3", true) }
        tvFour.setOnClickListener { appendOnExpression("4", true) }
        tvFive.setOnClickListener { appendOnExpression("5", true) }
        tvSix.setOnClickListener { appendOnExpression("6", true) }
        tvSeven.setOnClickListener { appendOnExpression("7", true) }
        tvEight.setOnClickListener { appendOnExpression("8", true) }
        tvNine.setOnClickListener { appendOnExpression("9", true) }
        tvZero.setOnClickListener { appendOnExpression("0", true) }
        tvDot.setOnClickListener { appendOnExpression(".", true) }


        //Operators
        tvPlus.setOnClickListener { appendOnExpression("+", false) }
        tvMinus.setOnClickListener { appendOnExpression("-", false) }
        tvMultiply.setOnClickListener { appendOnExpression("*", false) }
        tvDivide.setOnClickListener { appendOnExpression("/", false) }
        tvOpen.setOnClickListener { appendOnExpression("(", false) }
        tvClose.setOnClickListener { appendOnExpression(")", false) }


        tvClear.setOnClickListener {
            tvExpression.text=""
            tvResult.text=""
        }


        tvBack.setOnClickListener {
            val string = tvExpression.text.toString()
            if(string.isNotEmpty()) {
                tvExpression.text=string.substring(0,string.length-1)
            }
            tvResult.text=""
        }


        tvEqual.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longresult = result.toLong()
                if(result==longresult.toDouble())
                    tvResult.text=longresult.toString()
                else
                    tvResult.text=result.toString()
            } catch (e: Exception) {
                Log.d("Exception","message : " + e.message)
            }
        }
        animateTextColor(tvWelcome)

        // Set listeners and calculator logic
        setListeners()
    }
    private fun animateTextColor(textView: TextView) {
        val colorAnim = ObjectAnimator.ofInt(
            textView,
            "textColor",
            Color.RED,
            Color.YELLOW,
            Color.GREEN,
            Color.CYAN,
            Color.BLUE,
            Color.MAGENTA,
            Color.RED
        )

        colorAnim.duration = 4000 // duration for a complete color cycle
        colorAnim.setEvaluator(ArgbEvaluator())
        colorAnim.repeatCount = ObjectAnimator.INFINITE
        colorAnim.start()
    }

    private fun setListeners() {
        tvOne.setOnClickListener { appendOnExpression("1", true) }
        tvTwo.setOnClickListener { appendOnExpression("2", true) }
        // Add other listeners...
        // (remaining code for setListeners and calculator logic)
    }
    private fun appendOnExpression(string:String, canClear : Boolean ) {

        if(tvResult.text.isNotEmpty()) {
            tvExpression.text=""
        }

        if (canClear) {
            tvResult.text=""
            tvExpression.append(string)
        } else {
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text=""
        }
    }


}
