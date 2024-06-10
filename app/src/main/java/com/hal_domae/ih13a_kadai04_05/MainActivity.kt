package com.hal_domae.ih13a_kadai04_05

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hal_domae.ih13a_kadai04_05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var rightAnswer: String? = null // クイズの正解
    private var quizCount = 1
    private val quizData = mutableListOf(
        listOf("パンはパンでも食べられないパンは", "フライパン"),
        listOf("パンはパンでも食べられないパンは", "フライパン"),
        listOf("パンはパンでも食べられないパンは", "フライパン")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.inputAnswer.setOnKeyListener { _, keyCode, event ->
            // event.action == KeyEvent.ACTION_DOWNはキーが押された時
            // keyCode == KeyEvent.KEYCODE_ENTERエンターキーが押されたとき
            if(event.action == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_ENTER){
                checkAnswer()
                // trueを返すと他のキーイベントが発生しなくなる
                true
            } else {
                // falseを返すと返すと他のリスナーやシステムのキーイベント処理が続行される
                false
            }

        }
        showNextQuiz()
    }

    // クイズを出題するメソッド
    private fun showNextQuiz(){
        // 問題番号を表示
        // 問題番号はプレースホルダになっている
        binding.countLabel.text = getString(R.string.count_label, quizCount)

        val quiz = quizData[0]
        binding.questionLabel.text = quiz[0]
    }

    fun checkQuizCount(){

    }

    private fun checkAnswer(){
        // 入力された答えを取得
        val answerText = binding.inputAnswer.text.toString()
        // TODO 後で変える
        val alertTitle: String = "正解or不正解"

        val answerDialogFragment = AnswerDialogFragment()

        val bundle = Bundle().apply {
            putString("TITLE", alertTitle)
            putString("MESSAGE",alertTitle)
        }
        answerDialogFragment.arguments = bundle
        answerDialogFragment.isCancelable = false
        answerDialogFragment.show(supportFragmentManager, "my_dialog")
    }
}