package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {


    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val score = intent.getIntExtra("score",0)
        val totalSize = intent.getIntExtra("totalSize", 0)

        //점수보여주기
        binding.ScoreResult.text = getString(R.string.count_label, score, totalSize)


        //다시하기 버튼
        binding.resetbtn.setOnClickListener{
            val intent = Intent(this@ResultActivity, StartScreen::class.java)
            startActivity(intent)
        }

    }
}


