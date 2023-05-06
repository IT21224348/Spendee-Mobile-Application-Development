package com.example.spendee

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.spendee.DataClass.Bill_model
import com.example.spendee.DataClass.Goal_model
import com.example.spendee.databinding.ActivityAddBillBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class Add_bill : AppCompatActivity() {

    var nodeId = ""
    private lateinit var binding: ActivityAddBillBinding
    private lateinit var database: DatabaseReference
    private lateinit var userId: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize the binding
        binding=ActivityAddBillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set up navigation bar
        binding.homeBtn.setOnClickListener {

            val intent = Intent(this,Home::class.java)
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

        //Get the instance of the calendar
        val c = Calendar.getInstance()

        //Get the current year,month and date
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val date = c.get(Calendar.DAY_OF_MONTH)


        //Attach a click listener to the "Calender" Image button using view binding
        binding.pickerDate.setOnClickListener{
            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { datePicker, myear, mMonth, mDate ->
                // Format the selected date
                val selectedDate = "${mDate}-${mMonth + 1}-${myear}"
                // Set the selected date to the "loanDate" input field
                binding.loanDate.setText(selectedDate)
            },year,month,date)
            // Show the DatePickerDialog
            dpd.show()
        }
        // Set a click listener on the "cancel" button
        binding.cancleBTN.setOnClickListener {
            // Clear the input fields when the "cancel" button is clicked
            binding.loanName.setText("")
            binding.loanAmount.setText("")
            binding.loanDate.setText("")
        }

        binding.AddLoan.setOnClickListener {
            if(checkAllfields()){

                val userId = FirebaseAuth.getInstance().currentUser?.uid

                val Billname= binding.loanName.text.toString()
                val billamount = binding.loanAmount.text.toString().toDouble()
                val duedate = binding.loanDate.text.toString()


                database = FirebaseDatabase.getInstance().getReference("Bills")
                val bills = Bill_model(Billname,billamount,duedate)

                if (userId != null){

                    database.child(userId).push().setValue(bills).addOnSuccessListener {

                        binding.loanName.text.clear()
                        binding.loanAmount.text.clear()
                        binding.loanDate.text.clear()


                        Toast.makeText(this, "Bill added successfully", Toast.LENGTH_SHORT)
                            .show()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }


    }
    //Function to check whether input fields are empty
    private fun checkAllfields(): Boolean {

        //Get the inputs from input fields
        val loanName = binding.loanName.text.toString()
        val loanAmount = binding.loanAmount.text.toString()
        val date = binding.loanDate.text.toString()


        if (loanName.isEmpty()) {
            //Loan name field is empty,show a error message
            binding.loanName.error = getString(R.string.input_field_empty)
            return false
        }

        if (loanAmount.isEmpty()) {
            //Loan Amount field is empty,show a error message
            binding.loanAmount.error = getString(R.string.input_field_empty)
            return false
        }

        if (date.isEmpty()) {
            //Loan Date field is empty,show a error message
            binding.loanDate.error = getString(R.string.input_field_empty)
            return false
        }

        //All Fields are non empty
        return true
    }


    private val itemResultLauncher =
        registerForActivityResult<Intent, androidx.activity.result.ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result: androidx.activity.result.ActivityResult ->
            if (result.resultCode == 2) {
                val userId = FirebaseAuth.getInstance().currentUser?.uid
                val intent = result.data
                if (intent != null) {

                    nodeId = intent.getStringExtra("bill_id").toString()
                }
                if (userId != null) {
                    database = FirebaseDatabase.getInstance().getReference("Bills")

                    database.child(userId.toString()).child(nodeId).get().addOnSuccessListener {
                        if (it.exists()) {
                            binding.loanName.setText(it.child("name").value.toString())
                            binding.loanAmount.setText(it.child("target").value.toString())
                            binding.loanDate.setText(it.child("date").value.toString())
                            binding.updateBtn.visibility = View.VISIBLE
                            binding.deleteBtn.visibility = View.VISIBLE
                            binding.AddLoan.visibility = View.INVISIBLE
                            binding.cancleBTN.visibility = View.INVISIBLE


                        } else {
                            Toast.makeText(this, "Item not found", Toast.LENGTH_SHORT).show()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                    }

                }
            }

        }

    fun Delete_Data(view: View) {
        database = FirebaseDatabase.getInstance().getReference("Bills")
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            database.child(userId).child(nodeId).removeValue().addOnSuccessListener {

                binding.loanName.text.clear()
                binding.loanAmount.text.clear()
                binding.loanDate.text.clear()
                binding.updateBtn.visibility = View.INVISIBLE
                binding.deleteBtn.visibility = View.INVISIBLE
                binding.AddLoan.visibility = View.VISIBLE
                binding.cancleBTN.visibility = View.VISIBLE


                Toast.makeText(this, "Bill Deleted", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showlist(view: View) {
        var i: Intent
        i = Intent(this, View_bills::class.java)
        itemResultLauncher.launch(i)


    }


    fun Update_date(view: View) {
        val Target = binding.loanName.text.toString()
        val targetdate = binding.loanAmount.text.toString().toDouble()
        val targetamount = binding.loanDate.text.toString()


        database = FirebaseDatabase.getInstance().getReference("Bills")
        val bill =
            Bill_model(Target, targetdate, targetamount)
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if (userId != null) {

            database.child(userId).child(nodeId).setValue(bill).addOnSuccessListener {


                binding.loanName.text.clear()
                binding.loanAmount.text.clear()
                binding.loanDate.text.clear()
                binding.updateBtn.visibility = View.INVISIBLE
                binding.deleteBtn.visibility = View.INVISIBLE
                binding.AddLoan.visibility = View.VISIBLE
                binding.cancleBTN.visibility = View.VISIBLE

                Toast.makeText(this, "Target Updated successfully", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(this, "Failed to add loan", Toast.LENGTH_SHORT).show()
            }

        }
    }




}
