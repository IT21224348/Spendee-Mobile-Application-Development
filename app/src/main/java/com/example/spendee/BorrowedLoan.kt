package com.example.spendee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class BorrowedLoan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_borrowed_loan)

        val borrowed:TextView = findViewById(R.id.borrowed)
        val lent:TextView = findViewById(R.id.lent)
        val addloan:Button = findViewById(R.id.Loan_btn)
        val update_loan:ImageButton = findViewById(R.id.update_borrowed_btn)
        val profile_btn:ImageButton = findViewById(R.id.profile_btn)

        //navigation bar
        val home_btn: ImageButton = findViewById(R.id.home_btn)
        val transaction_btn2: ImageButton = findViewById(R.id.transaction_btn_nav)
        val target_btn: ImageButton = findViewById(R.id.target_btn_transaction)
        val bill_btn: ImageButton = findViewById(R.id.bill_btn)
        val loan_btn: ImageButton = findViewById(R.id.loan_btn)


        //Borowed Loan
        borrowed.setOnClickListener {
            val intent = Intent(this,BorrowedLoan::class.java)
            startActivity(intent)
        }

       //Lented Loan
        lent.setOnClickListener{
            val intent = Intent(this,Lent_loan::class.java)
            startActivity(intent)
        }

        //Add Loan
        addloan.setOnClickListener {
            val intent = Intent(this,Add_Loan::class.java)
            startActivity(intent)
        }

        //Update Loan
        update_loan.setOnClickListener {
            val intent = Intent(this,Update_loan::class.java)
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