package com.example.spendee.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spendee.DataClass.Lented_loan
import com.example.spendee.R

 class Lented_loan_Adapter(private  var loanlist:ArrayList<Lented_loan>):RecyclerView.Adapter<Lented_loan_Adapter.LentedLoanHolder>() {

     private lateinit var mListener :onItemClickListner

 interface onItemClickListner{

     fun onItemClick(position: Int)


 }
     fun setOnItemClickListner(listner:onItemClickListner){
         mListener = listner
     }

    class LentedLoanHolder(lLoanview:View,listner:onItemClickListner):RecyclerView.ViewHolder(lLoanview){

        val lonaName: TextView = lLoanview.findViewById(R.id.name)
        val amountl: TextView = lLoanview.findViewById(R.id.amount)
        val ldate: TextView = lLoanview.findViewById(R.id.lDate)
        val setDate: TextView = lLoanview.findViewById(R.id.setDate)

     init {
         lLoanview.setOnClickListener{

             listner.onItemClick(adapterPosition)

         }

        }

        }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LentedLoanHolder {
         val lLoanview= LayoutInflater.from(parent.context).inflate(R.layout.display_lented_loan_layout,parent,false)
         return LentedLoanHolder(lLoanview,mListener)
     }

     override fun getItemCount(): Int {
         return loanlist.size
     }

     override fun onBindViewHolder(holder: LentedLoanHolder, position: Int) {
         val currentloan = loanlist[position]
         holder.lonaName.setText(currentloan.name.toString())
         holder.amountl.setText(currentloan.amount.toString())
         holder.ldate.setText(currentloan.date.toString())
         holder.setDate.setText(currentloan.settle_date.toString())
     }


 }


