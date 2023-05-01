 package com.example.spendee

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import com.example.spendee.databinding.ActivityViewBillsBinding

 class View_bills : AppCompatActivity() {
     private lateinit var binding: ActivityViewBillsBinding
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

         //Initialize the binding
         binding = ActivityViewBillsBinding.inflate(layoutInflater)
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

         // Attach a click listener to the "Sign in" text using view binding
         binding.addBill.setOnClickListener {
             val intent = Intent(this,Add_bill::class.java)
             intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
             startActivity(intent)

         }



         }
     }

