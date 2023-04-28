package com.example.spendee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Goal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_goal)

        val addgoal1:Button = findViewById(R.id.add_goal)
        val profile_btn:ImageButton = findViewById(R.id.profile_btn)
        val update_goal:ImageButton = findViewById(R.id.update_goal)


         //Add Goal
        addgoal1.setOnClickListener{
            val intent = Intent(this,addgoal::class.java)
            startActivity(intent)
        }

        //Profile button
        profile_btn.setOnClickListener {
            val intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }

        //Update Goal
        update_goal.setOnClickListener {
            val intent= Intent(this,Update_goal::class.java)
            startActivity(intent);
        }

        //navigation bar
        val home_btn: ImageButton = findViewById(R.id.home_btn)
        val transaction_btn2: ImageButton = findViewById(R.id.transaction_btn_nav)
        val target_btn: ImageButton = findViewById(R.id.target_btn_transaction)
        val bill_btn: ImageButton = findViewById(R.id.bill_btn)
        val loan_btn: ImageButton = findViewById(R.id.loan_btn)







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