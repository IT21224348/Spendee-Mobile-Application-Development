package com.example.spendee

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class transaction : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        val update_trasaction: ImageButton = findViewById(R.id.update_trasaction_btn)

        //navigation bar
        val home_btn:ImageButton = findViewById(R.id.home_btn)
        val transaction_btn2:ImageButton = findViewById(R.id.transaction_btn_nav)
        val target_btn:ImageButton = findViewById(R.id.target_btn_transaction)
        val bill_btn:ImageButton = findViewById(R.id.bill_btn)
        val loan_btn:ImageButton = findViewById(R.id.loan_btn)

        update_trasaction.setOnClickListener {
            val intent = Intent(this,editincome::class.java)
            startActivity(intent)
        }

        //Navigation Bar

        //Home button
        home_btn.setOnClickListener {
            val intent = Intent(this,Home::class.java)
            startActivity(intent)
        }

        //Transaction Button
        transaction_btn2.setOnClickListener {
            val intent = Intent(this,transaction::class.java)
            startActivity(intent)
        }

        //Target Button
        target_btn.setOnClickListener {
            val intent = Intent(this,Goal::class.java)
            startActivity(intent)
        }

        //Bill Button
        bill_btn.setOnClickListener {
            val intent = Intent(this,Viewbills::class.java)
            startActivity(intent)
        }

        //Loan Button
        loan_btn.setOnClickListener {
            val intent = Intent(this,BorrowedLoan::class.java)
            startActivity(intent)
        }
    }
}