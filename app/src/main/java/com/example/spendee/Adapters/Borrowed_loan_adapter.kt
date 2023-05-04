package com.example.spendee.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.spendee.DataClass.Borrowed_loan
import com.example.spendee.R

class Borrowed_loan_adapter(private  var loanlist:ArrayList<Borrowed_loan>):RecyclerView.Adapter<Borrowed_loan_adapter.BorrowedLoanHolder>() {
     private lateinit var mListener: onItemClickListner
    interface  onItemClickListner{
          fun onItemClick(position: Int)
     }
     fun setOnItemClickListner(listner:onItemClickListner){
         mListener = listner
     }

    class BorrowedLoanHolder(bLoanview:View,listner: onItemClickListner):RecyclerView.ViewHolder(bLoanview){

            val lonaName:TextView = bLoanview.findViewById(R.id.name)
            val amountl:TextView = bLoanview.findViewById(R.id.amount)
            val ldate:TextView = bLoanview.findViewById(R.id.lDate)
            val setDate:TextView = bLoanview.findViewById(R.id.setDate)
  init{
       bLoanview.setOnClickListener{
           listner.onItemClick(adapterPosition)

       }

  }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BorrowedLoanHolder {
        val bLoanview = LayoutInflater.from(parent.context).inflate(R.layout.display_borrowed_loan,parent,false)
        return BorrowedLoanHolder(bLoanview,mListener)
    }

    override fun getItemCount(): Int {
      return loanlist.size
    }

    override fun onBindViewHolder(holder: BorrowedLoanHolder, position: Int) {
        val currentloan = loanlist[position]
        holder.lonaName.setText(currentloan.name.toString())
        holder.amountl.setText(currentloan.amount.toString())
        holder.ldate.setText(currentloan.date.toString())
        holder.setDate.setText(currentloan.settle_date.toString())


    }

}