package com.example.spendee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spendee.databinding.ActivityAddLoanBinding
import com.example.spendee.databinding.ActivityViewMainLoanBinding

class Main_Loan : AppCompatActivity() {
    private lateinit var binding: ActivityViewMainLoanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Initialize the binding
        binding = ActivityViewMainLoanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set up navigation bar
        binding.homeBtn.setOnClickListener {

            val intent = Intent(this,Home::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        binding.transactionBtnNav.setOnClickListener {
            val intent = Intent(this,Transaction::class.java)
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
            val intent = Intent(this, Main_Loan::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
        // Attach a click listener to the "Add loan" button using view binding
        binding.addLoan.setOnClickListener {
            val intent = Intent(this,Add_Loan::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }
}