package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    lateinit var resetbtn:Button
    lateinit var ScoreResult : TextView
    lateinit var one : ImageView
    lateinit var txt : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resetbtn = findViewById(R.id.resetbtn)
        ScoreResult = findViewById(R.id.ScoreResult)
        one = findViewById(R.id.one)
        txt = findViewById(R.id.txt)


        val score = intent.getIntExtra("score",0)
        val totalSize = intent.getIntExtra("totalsize", 0)

        //점수보여주기
        //(퀴즈 부분 만들면 추후에 추가예정)
//        ScoreResult.text = getString(R.string.count_label, score, totalSize)

        //정답개수에 따른 사진과 문구

        if (score == 5){
            one.setImageResource(R.drawable.five)
            txt.text = "너무 잘했어요! 조금 더 연습해봐요!"
        }else if (score > 5){
            one.setImageResource(R.drawable.ten)
            txt.text = "와우!! 너무 멋져요! 짱짱!"

        }


        //다시하기 버튼
        resetbtn.setOnClickListener{
            val intent = Intent(this@ResultActivity, StartScreen::class.java)
            startActivity(intent)
        }

    }
}




//결과값으로 넘어가는 코드

//    val intent(this@StartScreen, ResultActivity::class.java)
//    intent.putExtra("score", score)
//        intent.putExtra("totalSize", questionList.size)
//        startActivity(intent)
//        finish()

