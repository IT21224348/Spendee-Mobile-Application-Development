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

 class View_bills : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_view_bills)

         val profile_btn:ImageButton = findViewById(R.id.profile_btn)
         val add_bill:Button= findViewById(R.id.Loan_btn)
         val update_bill:ImageButton = findViewById(R.id.update_bill)


           //nnavigation Bar
         val home_btn: ImageButton = findViewById(R.id.home_btn)
         val transaction_btn2: ImageButton = findViewById(R.id.transaction_btn_nav)
         val target_btn: ImageButton = findViewById(R.id.target_btn_transaction)
         val bill_btn: ImageButton = findViewById(R.id.bill_btn)
         val loan_btn: ImageButton = findViewById(R.id.loan_btn)

         //Add Bill
         add_bill.setOnClickListener{
             val intent = Intent(this,Add_bill::class.java)
             startActivity(intent)
         }

         //Update Bill
         update_bill.setOnClickListener {
             val intent=Intent(this,Update_bill::class.java)
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

         val spinner: Spinner = findViewById(R.id.months)
// Create an ArrayAdapter using the string array and a default spinner layout
         ArrayAdapter.createFromResource(
             this,
             R.array.months_array,
             R.layout.selected_item
         ).also { adapter ->
             // Specify the layout to use when the list of choices appears
             adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
             // Apply the adapter to the spinner
             spinner.adapter = adapter

         }

         class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

             override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                 // An item was selected. You can retrieve the selected item using
                 // parent.getItemAtPosition(pos)
             }

             override fun onNothingSelected(parent: AdapterView<*>) {
                 // Another interface callback
             }
         }
     }
 }
