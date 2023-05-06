package com.example.spendee.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spendee.DataClass.Expense_model
import com.example.spendee.R

class Expense_adapter(private var expenseList:ArrayList<Expense_model>):RecyclerView.Adapter<Expense_adapter.ExpenseHolder>(){

    private lateinit var mListner:onItemClickListner

    interface onItemClickListner {

        fun onItemClick(position: Int)

    }

    fun setonItemClickListner(listner:onItemClickListner){

        mListner = listner

    }



    class ExpenseHolder(expenseview: View,listner:onItemClickListner):RecyclerView.ViewHolder(expenseview){

        val ename: TextView = expenseview.findViewById(R.id.name)
        val eamont: TextView = expenseview.findViewById(R.id.amount)
        val etype: TextView = expenseview.findViewById(R.id.IDate)
        val edate: TextView = expenseview.findViewById(R.id.setDate)

        init {
            expenseview.setOnClickListener{

                listner.onItemClick(adapterPosition)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseHolder {

        var expenseview = LayoutInflater.from(parent.context).inflate(R.layout.display_expense_layout,parent,false)
        return ExpenseHolder(expenseview,mListner)
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    override fun onBindViewHolder(holder: ExpenseHolder, position: Int) {
        val currentexpense = expenseList[position]

        holder.ename.setText(currentexpense.name.toString())
        holder.eamont.setText(currentexpense.amount.toString())
        holder.etype.setText(currentexpense.category.toString())
        holder.edate.setText(currentexpense.settle_date.toString())
    }

}