package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityStartScreenBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StartScreen : AppCompatActivity(){
    private lateinit var binding: ActivityStartScreenBinding

    lateinit var songFab:FloatingActionButton
    lateinit var mrFab:FloatingActionButton

    lateinit var player:MediaPlayer
    lateinit var mrPlayer:MediaPlayer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        //뷰 바인딩
        binding = ActivityStartScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        songFab=findViewById(R.id.songBtn) //노래버튼
        mrFab=findViewById(R.id.mrSongBtn) //mr버튼

        player= MediaPlayer.create(this, R.raw.multiplication_tables) //노래 플레이어
        mrPlayer= MediaPlayer.create(this, R.raw.mr_multiplication_tables) //mr 플레이어

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

        //게임 시작 버튼
        binding.gameStartBtn.setOnClickListener {
            val intent = Intent(this,QuizActivity::class.java)
            startActivity(intent)
        }

        //구구단 노래 실행
        binding.songBtn.setOnClickListener {
            if(player.isPlaying){
                pause()
            } else{
                start()
            }
        }
        //구구단 MR 노래 실행
        binding.mrSongBtn.setOnClickListener {
            if(mrPlayer.isPlaying){
                mrPause()
            } else{
                mrStart()
            }
        }
        //구구단 노래+ Mr 노래 정지
        binding.songStopBtn.setOnClickListener{
            stop()

        }


    }

    //구구단 재생
    private fun start(){
        songFab=findViewById<FloatingActionButton>(R.id.songBtn)
        songFab.setImageResource(R.drawable.ic_baseline_pause_24)

        player.start()

    }
    //구구단 일시정지
    private fun pause(){
        songFab=findViewById<FloatingActionButton>(R.id.songBtn)
        songFab.setImageResource(R.drawable.ic_baseline_play_arrow_24)

        player.pause()
    }
    //구구단 Mr 재생
    private fun mrStart(){
        mrFab=findViewById<FloatingActionButton>(R.id.mrSongBtn)
        mrFab.setImageResource(R.drawable.ic_baseline_pause_24)

        mrPlayer.start()
    }
    //구구단 Mr 일시정지
    private fun mrPause(){
        mrFab=findViewById<FloatingActionButton>(R.id.mrSongBtn)
        mrFab.setImageResource(R.drawable.ic_baseline_play_arrow_24)

        mrPlayer.pause()
    }
    //노래중단
    private fun stop(){

        songFab=findViewById<FloatingActionButton>(R.id.songBtn)
        mrFab=findViewById<FloatingActionButton>(R.id.mrSongBtn)

        songFab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        mrFab.setImageResource(R.drawable.ic_baseline_play_arrow_24)

        if(player.isPlaying){
            player.stop()
            player=MediaPlayer.create(this, R.raw.multiplication_tables)
        }

        if(mrPlayer.isPlaying){
            mrPlayer.stop()
            mrPlayer=MediaPlayer.create(this, R.raw.mr_multiplication_tables)
        }

    }

    override fun onDestroy () {
        super.onDestroy ()

    }

}