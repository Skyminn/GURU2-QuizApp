package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//로그인 성공한 이후에 넘어갈 창으로 임의로 만든거라 무시하시고 진짜 연결될 창에 연결해주시면 됩니다!
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}