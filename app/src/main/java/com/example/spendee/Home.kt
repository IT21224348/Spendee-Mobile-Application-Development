package com.example.spendee

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.spendee.databinding.ActivityHomeBinding
import com.example.spendee.databinding.ActivityLoginBinding

class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize the binding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val user_email = intent.getStringExtra("userEmail")
         val user_id  = intent.getStringExtra("firebaseId")

        binding.Email.text = "Email: $user_email"
        binding.uid.text = "Firebase Id: $user_id"



        }

    }
