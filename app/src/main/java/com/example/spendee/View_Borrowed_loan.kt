package com.example.spendee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spendee.Adapters.Borrowed_loan_adapter
import com.example.spendee.DataClass.Borrowed_loan
import com.example.spendee.databinding.ActivityAddLoanBinding
import com.example.spendee.databinding.ActivityUpdateLoanBinding
import com.example.spendee.databinding.ActivityViewBorrowedLoanBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class View_Borrowed_loan : AppCompatActivity() {
    private lateinit var binding: ActivityViewBorrowedLoanBinding
    private lateinit var db: DatabaseReference
    private lateinit var loanRecyclerView: RecyclerView
    private val nodelist = ArrayList<String>()
    private lateinit var loanArrayList: ArrayList<Borrowed_loan>
    private lateinit var userId: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Initialize the binding
        binding =ActivityViewBorrowedLoanBinding.inflate(layoutInflater)
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

        loanRecyclerView = binding.borrowedLoanList

        loanRecyclerView.layoutManager = LinearLayoutManager(this)
        loanRecyclerView.hasFixedSize()
        loanArrayList = arrayListOf<Borrowed_loan>()
        getLoanData()


    }

    private fun getLoanData() {
        db = FirebaseDatabase.getInstance().getReference("Borrowed Loans")

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if(userId != null){


            db.child(userId).addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                       for(loansnapshot in snapshot.children){
                            val loan = loansnapshot.getValue(Borrowed_loan::class.java)
                             println(loansnapshot)
                               loanArrayList.add(loan!!)
                               nodelist.add(loansnapshot.key.toString())
                               println(loansnapshot.key.toString())

                       }
                        var adapter = Borrowed_loan_adapter(loanArrayList)
                        loanRecyclerView.adapter = adapter
                        adapter.setOnItemClickListner(object:Borrowed_loan_adapter.onItemClickListner{
                            override fun onItemClick(position: Int) {
                               val nodepath : String = nodelist[position]
                                val intent = Intent()
                                intent.putExtra("loan_id",nodepath)
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