package com.nepplus.apipractice_okhttp_20220303

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nepplus.apipractice_okhttp_20220303.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupEvents()
        setVaules()
    }


    fun setupEvents(){

        binding.btnLogin.setOnClickListener {

            // id, 비번 추출
            val inputId = binding.edtId.text.toString()
            val inputPw = binding.edtPassword.text.toString()

            // API 서버에 아이디/ 비번을 보내서 실제로 회원인지 검사 => 로그인시도


        }
    }

    fun setVaules(){

    }

}