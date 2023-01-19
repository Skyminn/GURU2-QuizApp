package com.example.quizapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityStartScreenBinding

class StartScreen : AppCompatActivity(){
    private lateinit var binding: ActivityStartScreenBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        //뷰 바인딩
        binding = ActivityStartScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //게임 방법 버튼 눌렀을때 실행될 코드
        binding.gameRulesBtn.setOnClickListener {

            // Dialog만들기
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.activity_game_rules, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("게임 방법")

            val  mAlertDialog = mBuilder.show()

            val noButton = mDialogView.findViewById<Button>(R.id.closeBtn)
            noButton.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

        //게임 시작 버튼 눌렀을때 실행될 코드
        binding.gameStartBtn.setOnClickListener {
            //차후 구현
        }


    }
}