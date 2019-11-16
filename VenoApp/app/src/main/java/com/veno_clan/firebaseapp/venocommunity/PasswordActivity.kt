package com.veno_clan.firebaseapp.venocommunity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_password.*

class PasswordActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)


        set_email_btn.setOnClickListener { passowrd() }
    }

    fun passowrd(){
        val auth = FirebaseAuth.getInstance()
        if (input_email.text.toString().isNullOrEmpty()) {
            Toast.makeText(this, "이메일을 입력해 주세요", Toast.LENGTH_SHORT).show()
        } else {
            auth.sendPasswordResetEmail(input_email.text.toString()).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "이메일을 확인하셈", Toast.LENGTH_SHORT).show()
                    Log.d("이메일 보내기 로그", "이메일을 보냈습니다")
                }
            }
        }
    }
}
