package com.example.spendee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

         val income_btn: ImageButton = findViewById(R.id.add_income)
         val expense_btn: ImageButton = findViewById(R.id.add_expences)
         val transaction_btn :ImageButton = findViewById(R.id.transaction_btn)
         val profile_btn:ImageButton = findViewById(R.id.profile_btn)

           //navigation bar
        val home_btn:ImageButton = findViewById(R.id.home_btn)
        val transaction_btn2:ImageButton = findViewById(R.id.transaction_btn_nav)
        val target_btn:ImageButton = findViewById(R.id.target_btn_transaction)
        val bill_btn:ImageButton = findViewById(R.id.bill_btn)
        val loan_btn:ImageButton = findViewById(R.id.loan_btn)

            //Income Button
        income_btn.setOnClickListener{
            val intent = Intent(this,Add_income::class.java)
            startActivity(intent);
        }

           //Expense Button
        expense_btn.setOnClickListener {
            val intent = Intent(this,Add_expences::class.java)
            startActivity(intent)
        }

         //Transaction Button(main)
        transaction_btn.setOnClickListener {
            val intent = Intent(this,Transaction::class.java)
            startActivity(intent)
        }

         //Profile Button
        profile_btn.setOnClickListener {
            val intent = Intent(this,Profile::class.java)
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
            val intent = Intent(this,Transaction::class.java)
            startActivity(intent)
        }

        //Target Button
        target_btn.setOnClickListener {
            val intent = Intent(this,Goal::class.java)
            startActivity(intent)
        }

        //Bill Button
        bill_btn.setOnClickListener {
            val intent = Intent(this,View_bills::class.java)
            startActivity(intent)
        }

        //Loan Button
        loan_btn.setOnClickListener {
            val intent = Intent(this,View_Borrowed_loan::class.java)
            startActivity(intent)
        }

    }
}