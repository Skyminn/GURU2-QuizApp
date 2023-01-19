package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var localDB: LocalDB
    val DATABASE_VERSION = 1
    val DATABASE_NAME = "LocalDB.db"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        localDB= LocalDB(this, DATABASE_NAME,null, DATABASE_VERSION) // SQLite 모듈 생성

        binding.btnRegister.setOnClickListener { view->
            if(binding.editId.text.isEmpty()||binding.editPw.text.isEmpty()||binding.editPwRe.text.isEmpty()){// 값이 전부 입력되지 않은경우
                Toast.makeText(this,"값을 전부 입력해주세요..",Toast.LENGTH_LONG).show()
            }else{
                if(binding.editPw.text.toString().equals(binding.editPwRe.text.toString())){//패스워드/패스워드 확인이 일치
                    if(localDB.checkIdExist(binding.editId.text.toString())){// 아이디 중복 확인
                        Toast.makeText(this,"아이디가 이미 존재합니다.",Toast.LENGTH_LONG).show()
                    }else{// 회원가입 완료
                        localDB.registerUser(binding.editId.text.toString(),binding.editPw.text.toString())
                        Toast.makeText(this,"회원가입이 완료되었습니다.",Toast.LENGTH_LONG).show()
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }
                }else{ // 패스워드/패스워드 확인이 일치하지 않음
                    Toast.makeText(this,"패스워드가 틀렸습니다.",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}