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

    private var rightAnswer: String? = null
    private var quizCount = 0
    private var currentQuizIndex = 0 // 現在のクイズのインデックス
    private val quizData = mutableListOf(
        listOf("パンはパンでも食べられないパンは", "huraipan"),
        listOf("カッパは何色", "赤"),
        listOf("正解いちオオカミに近い遺伝子をもっている犬は", "sibainu"),
        listOf("熊本にクマはいるか", "inai"),
        listOf("アディスアベバに平均年収は", "100")

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
    // クイズを出題するメソッド
    private fun showNextQuiz() {
        // 問題番号を表示
        binding.countLabel.text = getString(R.string.count_label, currentQuizIndex + 1)

        // 現在のクイズを取得し、問題文を表示
        val currentQuiz = quizData[currentQuizIndex]
        binding.questionLabel.text = currentQuiz[0]

        // 正解を保持
        rightAnswer = currentQuiz[1]

        // 入力欄をクリア
        binding.inputAnswer.text.clear()
    }

    fun checkQuizCount(){


    }

    private fun checkAnswer() {
        val answerText = binding.inputAnswer.text.toString()
        val isCorrect = answerText.equals(rightAnswer, ignoreCase = true)
        val alertTitle = if (isCorrect) "正解" else "不正解"
        val alertMessage = if (isCorrect) "正解です!" else "不正解です。正解は「$rightAnswer」です。"

        val answerDialogFragment = AnswerDialogFragment.newInstance(alertTitle, alertMessage)
        answerDialogFragment.show(supportFragmentManager, "answer_dialog")

        if (currentQuizIndex < quizData.size - 1) {
            currentQuizIndex++
        } else {
            currentQuizIndex = 0 // 1問目に戻る
        }
        showNextQuiz()
    }
}