package com.example.spendee

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class Signup : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

          val register_login:TextView = findViewById(R.id.register_login)


        register_login.setOnClickListener {

            val intent = Intent(this,Login::class.java)
            startActivity(intent)


        }

        }
    }
