package com.example.spendee

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spendee.DataClass.Borrowed_loan
import com.example.spendee.DataClass.Lented_loan
import com.example.spendee.databinding.ActivityAddLoanBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

class Add_Loan : AppCompatActivity() {

     private  lateinit var binding: ActivityAddLoanBinding
     private lateinit var database: DatabaseReference
    private lateinit var userId: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize the binding
        binding = ActivityAddLoanBinding.inflate(layoutInflater)
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

        //Get the instance of the calendar
        val c = Calendar.getInstance()

        //Get the current year,month and date
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val date = c.get(Calendar.DAY_OF_MONTH)
        //Attach a click listener to the "Calender" Image button using view binding
        binding.pickerDate.setOnClickListener{
            val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { datePicker, myear, mMonth, mDate ->
                // Format the selected date
                val selectedDate = "${mDate}-${mMonth + 1}-${myear}"
                // Set the selected date to the "loanDate" input field
                binding.loanDate.setText(selectedDate)
            },year,month,date)
            // Show the DatePickerDialog
            dpd.show()
        }

        //Attach a click listener to the "Calender" Image button using view binding
        binding.pickerDate1.setOnClickListener{
            val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { datePicker, myear, mMonth, mDate ->
                // Format the selected date
                val selectedDate = "${mDate}-${mMonth + 1}-${myear}"
                // Set the selected date to the "loanDate" input field
                binding.loanSettle.setText(selectedDate)
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
            binding.loanSettle.setText("")

        }

        // Set a click listener on the "Add" button
        binding.AddLoan.setOnClickListener {
            if(checkAllfields()){

            val SelectedId = binding.LoanType.checkedRadioButtonId

            val userId = FirebaseAuth.getInstance().currentUser?.uid

            //For Borrowed Loan
            if (SelectedId == R.id.borrowed_loan) {


                val BorrowedName = binding.loanName.text.toString()
                val BorrowedAmount = binding.loanAmount.text.toString().toDouble()
                val BorrowedDate = binding.loanDate.text.toString()
                val SettleDate = binding.loanSettle.text.toString()

                database = FirebaseDatabase.getInstance().getReference("Borrowed Loans")
                val borrowed_loan =
                    Borrowed_loan(BorrowedName, BorrowedAmount, BorrowedDate, SettleDate)


                if (userId != null) {

                    database.child(userId).push().setValue(borrowed_loan).addOnSuccessListener {

                        database.child(userId)
                            .addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                    println("onDataChange called")
                                    var totalAmount = 0.0
                                    for (loanSnapshot in dataSnapshot.children) {
                                        println(loanSnapshot)
                                        try {
                                            val loan =
                                                loanSnapshot.getValue(Borrowed_loan::class.java)
                                            if (loan != null) {
                                                totalAmount += loan.amount ?: 0.0
                                            }
                                        } catch (ex: DatabaseException) {
                                            ex.printStackTrace()
                                        }

                                    }
                                    println("Total amount computed: $totalAmount")
                                    database.child(userId).child("total_amount")
                                        .setValue(totalAmount)

                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    // Handle the error
                                }
                            })

                        binding.loanName.text.clear()
                        binding.loanAmount.text.clear()
                        binding.loanDate.text.clear()
                        binding.loanSettle.text.clear()

                        Toast.makeText(this, "Loan added successfully", Toast.LENGTH_SHORT).show()

                    }.addOnFailureListener {

                        Toast.makeText(this, "Failed to add loan", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            //For Lent Loans
            if (SelectedId == R.id.lented_loan) {

                val LentedName = binding.loanName.text.toString()
                val LentedAmount = binding.loanAmount.text.toString().toDouble()
                val LentedDate = binding.loanDate.text.toString()
                val SettleDate = binding.loanSettle.text.toString()

                database = FirebaseDatabase.getInstance().getReference("Lented Loans")
                val lented_loan = Lented_loan(LentedName, LentedAmount, LentedDate, SettleDate)

                if (userId != null) {
                    database.child(userId).push().setValue(lented_loan).addOnSuccessListener {

                        database.child(userId)
                            .addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                    println("onDataChange called")
                                    var totalAmount = 0.0
                                    for (loanSnapshot in dataSnapshot.children) {
                                        println(loanSnapshot)
                                        try {
                                            val loan =
                                                loanSnapshot.getValue(Lented_loan::class.java)
                                            if (loan != null) {
                                                totalAmount += loan.amount ?: 0.0
                                            }
                                        } catch (ex: DatabaseException) {
                                            ex.printStackTrace()
                                        }
                                    }
                                    println("Total amount computed: $totalAmount")
                                    database.child(userId).child("total_amount")
                                        .setValue(totalAmount)

                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    // Handle the error
                                }
                            })

                        binding.loanName.text.clear()
                        binding.loanAmount.text.clear()
                        binding.loanDate.text.clear()
                        binding.loanSettle.text.clear()

                        Toast.makeText(this, "Loan added successfully", Toast.LENGTH_SHORT).show()

                    }.addOnFailureListener {

                        Toast.makeText(this, "Failed to add loan", Toast.LENGTH_SHORT).show()
                    }
                }

            }
         }
        }
    }

    //Function to check whether input fields are empty
    private fun checkAllfields():Boolean{

        //Get the inputs from input fields
        val loanName = binding.loanName.text.toString()
        val loanAmount = binding.loanAmount.text.toString()
        val date = binding.loanDate.text.toString()
        val settleDate = binding.loanSettle.text.toString()

        if(loanName.isEmpty()){
            //Loan name field is empty,show a error message
            binding.loanName.error = getString(R.string.input_field_empty)
            return false
        }

        if(loanAmount.isEmpty()){
            //Loan Amount field is empty,show a error message
            binding.loanAmount.error = getString(R.string.input_field_empty)
            return false
        }

        if(date.isEmpty()){
            //Loan Date field is empty,show a error message
            binding.loanDate.error = getString(R.string.input_field_empty)
            return false
        }

        if (settleDate.isEmpty()){
            //Loan settle Date field is empty,show a error message
            binding.loanSettle.error =getString(R.string.input_field_empty)
            return false
        }
        //All Fields are non empty
        return true
    }



}

