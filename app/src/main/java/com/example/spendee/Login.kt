package com.example.spendee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.IntSummaryStatistics

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       val login_register: TextView = findViewById(R.id.login_register)
       val login_btn:Button = findViewById(R.id.login_btn)

        login_register.setOnClickListener {
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }
        login_btn.setOnClickListener{
            val intent = Intent(this,Home::class.java)
            startActivity(intent)

        }


    }
}