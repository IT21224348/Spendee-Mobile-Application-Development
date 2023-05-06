package com.example.spendee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spendee.Adapters.Borrowed_loan_adapter
import com.example.spendee.Adapters.Goal_Adapter
import com.example.spendee.DataClass.Borrowed_loan
import com.example.spendee.DataClass.Goal_model
import com.example.spendee.databinding.ActivityViewGoalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Goal : AppCompatActivity() {
    lateinit var binding : ActivityViewGoalBinding
    private lateinit var db: DatabaseReference
    private lateinit var goalRecyclerView: RecyclerView
    private lateinit var goalArrayList: ArrayList<Goal_model>
    private val nodelist = ArrayList<String>()
    private lateinit var userId: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize the binding
        binding = ActivityViewGoalBinding.inflate(layoutInflater)
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
        binding.addGoal.setOnClickListener {
            val intent =Intent(this,Add_Goal::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        goalRecyclerView= binding.goalList
        goalRecyclerView.layoutManager = LinearLayoutManager(this)
        goalRecyclerView.hasFixedSize()
        goalArrayList = arrayListOf<Goal_model>()
        getGoalData()





    }

    private fun getGoalData() {

        db = FirebaseDatabase.getInstance().getReference("Goals")

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if(userId != null){
                 db.child(userId).addValueEventListener(object : ValueEventListener{
                     override fun onDataChange(snapshot: DataSnapshot) {
                         if(snapshot.exists()){
                             for(goalsnapshot in snapshot.children){
                                 val goal = goalsnapshot.getValue(Goal_model::class.java)
                                 println(goalsnapshot)
                                 goalArrayList.add(goal!!)
                                 nodelist.add(goalsnapshot.key.toString())


                             }
                             var adapter = Goal_Adapter(goalArrayList)
                             goalRecyclerView.adapter =  adapter

                             adapter.setOnItemClickListner(object:Goal_Adapter.onItemClickListner{
                                 override fun onItemClick(position: Int) {
                                     val nodepath : String = nodelist[position]
                                     val intent = Intent()
                                     intent.putExtra("goal_id",nodepath)
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