package com.veno_clan.firebaseapp.venocommunity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.veno_clan.firebaseapp.venocommunity.model.UserModel
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {


    var auth: FirebaseAuth? = null
    val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        signup.setOnClickListener { signup_check() }
        jungbo.setOnClickListener { jungbo() }

    }

    fun jungbo(){
        startActivity(Intent(this, JungboActivity::class.java))
        Toast.makeText(this, "개인정보 약관을 꼼꼼히 읽어 주세요",Toast.LENGTH_SHORT).show()
    }

    fun signup_check(){
        if(email_signup.text.toString().isNullOrEmpty() || password_signup.text.toString().isNullOrEmpty() || nikname_signup.text.toString().isNullOrEmpty()){
                Toast.makeText(this, getString(R.string.signup_fail_null), Toast.LENGTH_SHORT).show()
        } else {
            createAndLoginEmail()
        }
    }

    fun createAndLoginEmail(){
        auth?.createUserWithEmailAndPassword(email_signup.text.toString(), password_signup.text.toString())
            ?.addOnCompleteListener { task ->

                if(task.isSuccessful) {
                    var uid = task.result!!.user?.uid
                    var email = task.result!!.user?.email
                    var userModel = UserModel()
                    userModel.userName = nikname_signup?.text.toString()
                    userModel.userEmail = email_signup?.text.toString()
                    userModel.password = password_signup?.text.toString()
                    userModel.uid = FirebaseAuth.getInstance().currentUser!!.uid

                    firestore.collection("user").document(uid!!).set(userModel).addOnCompleteListener { task ->
                            Toast.makeText(this, getString(R.string.signup_complate),Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                } else {
                    Toast.makeText(this, task.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
}

