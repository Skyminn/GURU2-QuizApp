package com.example.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.quizapp.MainActivity
import com.example.quizapp.R

class ResultActivity : AppCompatActivity() {

    lateinit var resetbtn:Button
    lateinit var ScoreResult : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resetbtn = findViewById(R.id.resetbtn)
        ScoreResult = findViewById(R.id.ScoreResult)

        val score = intent.getIntExtra("score",0)
        val totalSize = intent.getIntExtra("totalsize", 0)

        //점수보여주기



        //다시하기 버튼
        resetbtn.setOnClickListener{
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
}