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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spendee.Adapters.Bill_adapter
import com.example.spendee.Adapters.Goal_Adapter
import com.example.spendee.DataClass.Bill_model
import com.example.spendee.DataClass.Goal_model
import com.example.spendee.databinding.ActivityViewBillsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

 class View_bills : AppCompatActivity() {
     private lateinit var binding: ActivityViewBillsBinding
     private lateinit var billRecyclerView: RecyclerView
     private lateinit var billArrayList: ArrayList<Bill_model>
     private lateinit var db: DatabaseReference
     private val nodelist = ArrayList<String>()
     private lateinit var userId: FirebaseAuth
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

         binding.targetBtnTransaction.setOnClickListener {
             val intent = Intent(this,MainGoal::class.java)
             intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
             startActivity(intent)
         }

         binding.billBtn.setOnClickListener {
             val intent = Intent(this,Main_Bill::class.java)
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

         billRecyclerView= binding.billList
         billRecyclerView.layoutManager = LinearLayoutManager(this)
         billRecyclerView.hasFixedSize()
         billArrayList = arrayListOf<Bill_model>()
         getGoalData()







         }

     private fun getGoalData() {


         db = FirebaseDatabase.getInstance().getReference("Bills")

         val userId = FirebaseAuth.getInstance().currentUser?.uid

         if(userId != null){
             db.child(userId).addValueEventListener(object :ValueEventListener{
                 override fun onDataChange(snapshot: DataSnapshot) {
                     if(snapshot.exists()){

                         for(billsnapshot in snapshot.children){
                             val bill = billsnapshot.getValue(Bill_model::class.java)
                             println(billsnapshot)
                             billArrayList.add(bill!!)
                             nodelist.add(billsnapshot.key.toString())
                         }

                         var adapter = Bill_adapter(billArrayList)
                         billRecyclerView.adapter = adapter

                         adapter.setOnItemClickListner(object :Bill_adapter.onItemClickListner{
                             override fun onItemClick(position: Int) {
                                 val nodepath : String = nodelist[position]
                                 val intent = Intent()
                                 intent.putExtra("bill_id",nodepath)
                                 setResult(2,intent)
                                 finish()
                             }


                         })



                     }
                 }

                 override fun onCancelled(error: DatabaseError) {
                     TODO("Not yet implemented")
                 }


             })



         }


     }
 }

