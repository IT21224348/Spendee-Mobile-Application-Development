package com.example.spendee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spendee.Adapters.Borrowed_loan_adapter
import com.example.spendee.Adapters.Income_adapter
import com.example.spendee.DataClass.Goal_model
import com.example.spendee.DataClass.Income_model
import com.example.spendee.databinding.ActivityViewGoalBinding
import com.example.spendee.databinding.ActivityViewIncomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class View_income : AppCompatActivity() {

    private lateinit var binding: ActivityViewIncomeBinding
    private lateinit var db: DatabaseReference
    private lateinit var incomeRecyclerView: RecyclerView
    private lateinit var incomeArrayList: ArrayList<Income_model>
    private val nodelist = ArrayList<String>()
    private lateinit var userId: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize the binding
        binding = ActivityViewIncomeBinding.inflate(layoutInflater)
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

        // Attach a click listener to the "Add goal" button using view binding
        binding.addincome.setOnClickListener {
            val intent = Intent(this,Add_income::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        incomeRecyclerView= binding.incomelist
        incomeRecyclerView.layoutManager = LinearLayoutManager(this)
        incomeRecyclerView.hasFixedSize()
       incomeArrayList = arrayListOf<Income_model>()
        getGoalData()





    }

    private fun getGoalData() {

        db = FirebaseDatabase.getInstance().getReference("Incomes")

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if(userId != null){

            db.child(userId).addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()) {
                        for (incomesnapshot in snapshot.children) {
                            val income = incomesnapshot.getValue(Income_model::class.java)
                            println(incomesnapshot)
                            incomeArrayList.add(income!!)
                            nodelist.add(incomesnapshot.key.toString())



                        }
                        var adapter = Income_adapter(incomeArrayList)
                        incomeRecyclerView.adapter = adapter

                        adapter.setOnItemClickListner(object :Income_adapter.onItemClickListner{
                            override fun onItemClick(position: Int) {
                                val nodepath : String = nodelist[position]
                                val intent = Intent()
                                intent.putExtra("income_id",nodepath)
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