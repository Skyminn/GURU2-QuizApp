package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Dimension
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.quizapp.databinding.ActivityQuizBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding:ActivityQuizBinding

    private var currentPosition: Int = 1 // 질문 위치
    private var selectedOption: Int = 0 // 선택 답변 값
    private var score: Int = 0 // 점수

    private var time = 0 // 시간
    private var timerTask: Timer? = null // 타이머

    private lateinit var questionList: ArrayList<Question> // 문제 리스트

    lateinit var secTextView : TextView
    lateinit var milliTextView : TextView
    lateinit var totalTime : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 질문 리스트 가져오기
        questionList = QuestionData.getQuestion()

        //화면 세팅
        getQuestionData()

        binding.button.setOnClickListener(this)
        binding.button2.setOnClickListener(this)
        binding.button3.setOnClickListener(this)
        binding.button4.setOnClickListener(this)
        binding.button5.setOnClickListener(this)
        binding.button6.setOnClickListener(this)
        binding.button7.setOnClickListener(this)
        binding.button8.setOnClickListener(this)
        binding.button9.setOnClickListener(this)

        secTextView = findViewById<TextView>(R.id.secTextView)
        milliTextView = findViewById<TextView>(R.id.milliTextView)
    }

    // 답변 체크
    private fun check() {
        if (selectedOption != 0) {
            val question = questionList[currentPosition - 1]
            // 타이머 시작
            start()

            // 정답 체크 (선택 답변과 정답을 비교)
            if (selectedOption != question.correct_answer) { // 오답
                setColor(selectedOption, R.drawable.wrong_option_background)

                callDialog("오답", "정답 ${question.correct_answer}")
            }else{
                score++
            }

            setColor(question.correct_answer, R.drawable.correct_option_background)

            //위치값 상승
            currentPosition++

            // 정답 0.5 초 지속 후 다음 문제 출력
            Handler(Looper.getMainLooper()).postDelayed({

                if (currentPosition <= questionList.size)
                    getQuestionData()

            }, 500)

            /**
             * 퀴즈 끝나는 부분
             * 결과 화면으로 연결하면 될 것 같습니다.
             */
            // 마지막 문제 정답 체크 시 알림
            if (currentPosition > questionList.size) {
                recordLapTime()
                Toast.makeText(this, "끝", Toast.LENGTH_SHORT).show()

                // 결과화면으로 이어지는 코드
                intent = Intent(this@QuizActivity, ResultActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("totalSize", questionList.size)
                intent.putExtra("recode", totalTime)

                startActivity(intent)
            }
        }
        // 선택값 초기화
        selectedOption = 0
    }

    /**
     * 답변 색상 변경
     */
    private fun setColor(opt: Int, color: Int) {
        when(opt) {
            1 -> binding.button.background = ContextCompat.getDrawable(this, color)
            2 -> binding.button2.background = ContextCompat.getDrawable(this, color)
            3 -> binding.button3.background = ContextCompat.getDrawable(this, color)
            4 -> binding.button4.background = ContextCompat.getDrawable(this, color)
            5 -> binding.button5.background = ContextCompat.getDrawable(this, color)
            6 -> binding.button6.background = ContextCompat.getDrawable(this, color)
            7 -> binding.button7.background = ContextCompat.getDrawable(this, color)
            8 -> binding.button8.background = ContextCompat.getDrawable(this, color)
            9 -> binding.button9.background = ContextCompat.getDrawable(this, color)
        }
    }

    /**
     * 정답 확인 다이얼로그
     */
    private fun callDialog(alertTitle: String, correctAnswer: String) {
        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("정답: $correctAnswer")
            .setPositiveButton("OK") {
                dialogInterface, i ->
                dialogInterface.dismiss()
            }
    }

    private fun getQuestionData() {

        //답변 설정 초기화
        setOptionStyle()

        if((currentPosition-1) == 2 || (currentPosition-1) == 3) {
            binding.questionText.setTextSize(Dimension.SP, 22F)
        } else
            binding.questionText.setTextSize(Dimension.SP, 34F)
        // 질문 변수에 담기
        val question = questionList[currentPosition - 1]

        // 진행 상태바 위치
        binding.progressBar.progress = currentPosition

        // 상태바 최댓값
        binding.progressBar.max = questionList.size

        // 현재 위치 표시
        binding.progressText.text =
            getString(R.string.count_label, currentPosition, questionList.size)

        // 질문 표시
        binding.questionText.text = question.question

        // 답변 표시
        binding.button.text = "1"
        binding.button2.text = "2"
        binding.button3.text = "3"
        binding.button4.text = "4"
        binding.button5.text = "5"
        binding.button6.text = "6"
        binding.button7.text = "7"
        binding.button8.text = "8"
        binding.button9.text = "9"
    }

    /** 답변 스타일 설정 */

    private fun setOptionStyle() {
        val optionList:ArrayList<Button> = arrayListOf()
        optionList.add(binding.button)
        optionList.add(binding.button2)
        optionList.add(binding.button3)
        optionList.add(binding.button4)
        optionList.add(binding.button5)
        optionList.add(binding.button6)
        optionList.add(binding.button7)
        optionList.add(binding.button8)
        optionList.add(binding.button9)

        // 답변 텍스트뷰 설정
        for (op in optionList) {
            op.setTextColor(Color.parseColor("#555151"))
            op.background = ContextCompat.getDrawable(this, R.drawable.option_background)
            op.typeface = Typeface.DEFAULT
        }
    }

    /**답변 선택 이벤트 */
    private fun selectedOptionStyle(view: Button, opt: Int) {
        // 옵션 초기화
        setOptionStyle()

        //위치 담기
        selectedOption = opt
        view.setTextColor((Color.parseColor("#5F00FF")))
        view.background = ContextCompat.getDrawable(this, R.drawable.selected_option_background)
        view.typeface = Typeface.DEFAULT_BOLD

        // 답변 체크
        check()
    }
    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.button -> selectedOptionStyle(binding.button, 1)
            R.id.button2 -> selectedOptionStyle(binding.button2, 2)
            R.id.button3 -> selectedOptionStyle(binding.button3, 3)
            R.id.button4 -> selectedOptionStyle(binding.button4, 4)
            R.id.button5 -> selectedOptionStyle(binding.button5, 5)
            R.id.button6 -> selectedOptionStyle(binding.button6, 6)
            R.id.button7 -> selectedOptionStyle(binding.button7, 7)
            R.id.button8 -> selectedOptionStyle(binding.button8, 8)
            R.id.button9 -> selectedOptionStyle(binding.button9, 9)
        }
    }

    /**
     * 타이머 함수 구현
     */

    private fun start() {
        timerTask = timer(period=10) {
            time++
            val sec = time / 100
            val milli = time % 100
            runOnUiThread {
                secTextView.text = "$sec"
                milliTextView.text = "$milli"
            }
        }
    }

    private fun recordLapTime() {
        val lapTime = this.time
        totalTime = "${lapTime / 100}.${lapTime % 100}"
    }
}