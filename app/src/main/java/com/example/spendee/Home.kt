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


        //Set up navigation bar
        binding.homeBtn.setOnClickListener {
            val intent= Intent(this,Home::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        binding.transactionBtnNav.setOnClickListener {
            val intent =Intent(this,Transaction::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        binding.targetBtnTransaction.setOnClickListener {
            val intent = Intent(this,Goal::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        binding.billBtn.setOnClickListener {
            val intent = Intent(this,View_bills::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        binding.loanBtn.setOnClickListener {
            val intent =Intent(this,Main_Loan::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
         // Attach a click listener to the "Add income" Imagebutton using view binding
        binding.addIncome.setOnClickListener {
            val intent = Intent(this,Add_income::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        // Attach a click listener to the "Add expenses" Imagebutton using view binding
        binding.addExpences.setOnClickListener {
            val intent = Intent(this,Add_expences::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }


        }

    }
