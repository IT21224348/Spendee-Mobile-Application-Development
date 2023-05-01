package com.example.spendee

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.spendee.databinding.ActivityAddLoanBinding
import java.util.*

class Add_Loan : AppCompatActivity() {

     private  lateinit var binding: ActivityAddLoanBinding
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




    }
}