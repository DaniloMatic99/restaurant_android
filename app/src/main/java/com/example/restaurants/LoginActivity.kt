package com.example.restaurants

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var preferance : SharedPreferences = getSharedPreferences("MYPREFS", MODE_PRIVATE)

        var email :String
        var password :String

        buttonLogin.setOnClickListener {
            email = edEmail.text.trim().toString()
            password = edPassword.text.trim().toString()

            if(email.isNotEmpty() && password.isNotEmpty()){

                var regEmail : String? = preferance.getString("email","Email is Incorrect.")
                var regPassword : String? = preferance.getString("password","Password is Incorrect.")
                println()
                if(regEmail.toString() == email && regPassword.toString() == password) {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish();
                }
                else{
                    Toast.makeText(this,"Email or Password are incorrect", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"Input required", Toast.LENGTH_LONG).show()

            }
        }
        tvRegister.setOnClickListener{
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
