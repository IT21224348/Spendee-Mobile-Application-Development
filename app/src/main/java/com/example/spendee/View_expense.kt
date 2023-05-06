package com.example.spendee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spendee.Adapters.Expense_adapter
import com.example.spendee.Adapters.Income_adapter
import com.example.spendee.DataClass.Expense_model
import com.example.spendee.DataClass.Income_model
import com.example.spendee.databinding.ActivityViewExpenseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.EventListener

class View_expense : AppCompatActivity() {

    private lateinit var binding: ActivityViewExpenseBinding
    private lateinit var db: DatabaseReference
    private lateinit var expenseRecyclerView: RecyclerView
    private lateinit var expenseArrayList: ArrayList<Expense_model>
    private val nodelist = ArrayList<String>()
    private lateinit var userId: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewExpenseBinding.inflate(layoutInflater)
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
        binding.addexpense.setOnClickListener {
            val intent = Intent(this,Add_expences::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        expenseRecyclerView= binding.eList
        expenseRecyclerView.layoutManager = LinearLayoutManager(this)
        expenseRecyclerView.hasFixedSize()
        expenseArrayList = arrayListOf<Expense_model>()
        getExpenseData()

    }

    private fun getExpenseData() {
        db = FirebaseDatabase.getInstance().getReference("Expenses")

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if(userId != null){

            db.child(userId).addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()) {
                        for (incomesnapshot in snapshot.children) {
                            val income = incomesnapshot.getValue(Expense_model::class.java)
                            println(incomesnapshot)
                            expenseArrayList.add(income!!)
                            nodelist.add(incomesnapshot.key.toString())

                        }


                        var adapter = Expense_adapter(expenseArrayList)
                        expenseRecyclerView.adapter = adapter


                        adapter.setonItemClickListner(object :Expense_adapter.onItemClickListner{
                            override fun onItemClick(position: Int) {
                                val nodepath : String = nodelist[position]
                                val intent = Intent()
                                intent.putExtra("expense_id",nodepath)
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