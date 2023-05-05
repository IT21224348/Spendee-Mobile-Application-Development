package com.example.spendee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spendee.Adapters.Goal_Adapter
import com.example.spendee.Adapters.Lented_loan_Adapter
import com.example.spendee.DataClass.Borrowed_loan
import com.example.spendee.DataClass.Lented_loan
import com.example.spendee.databinding.ActivityViewLentLoanBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class View_lent_loan : AppCompatActivity() {

     private  lateinit var binding:ActivityViewLentLoanBinding
    private lateinit var db: DatabaseReference
    private lateinit var loanRecyclerView: RecyclerView
    private lateinit var loanArrayList: ArrayList<Lented_loan>
    private val nodelist = ArrayList<String>()
    private lateinit var userId: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize the binding
        binding=ActivityViewLentLoanBinding.inflate(layoutInflater)
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
        // Attach a click listener to the "Add loan" button using view binding
        binding.addLent.setOnClickListener {
            val intent = Intent(this,Add_Lent_Loan::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }



        loanRecyclerView = binding.lList

        loanRecyclerView.layoutManager = LinearLayoutManager(this)
        loanRecyclerView.hasFixedSize()
        loanArrayList = arrayListOf<Lented_loan>()
        getLoanData()







    }

    private fun getLoanData() {
        db = FirebaseDatabase.getInstance().getReference("Lented Loans")
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if(userId != null){
             db.child(userId).addValueEventListener(object :ValueEventListener{
                 override fun onDataChange(snapshot: DataSnapshot) {
                     if(snapshot.exists()){

                         for(loansnapshot in snapshot.children){
                             val loan = loansnapshot.getValue(Lented_loan::class.java)
                             println(loansnapshot)
                             loanArrayList.add(loan!!)
                             nodelist.add(loansnapshot.key.toString())


                         }
                         var adpter = Lented_loan_Adapter(loanArrayList)
                         loanRecyclerView.adapter = adpter
                         adpter.setOnItemClickListner(object :Lented_loan_Adapter.onItemClickListner{
                             override fun onItemClick(position: Int) {
                                 val nodepath : String = nodelist[position]
                                 val intent = Intent()
                                 intent.putExtra("loan_id",nodepath)
                                 setResult(3,intent)
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