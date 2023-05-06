package com.example.spendee.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spendee.DataClass.Income_model
import com.example.spendee.R
import java.text.DecimalFormat

class Income_adapter(private var incomelist:ArrayList<Income_model>):RecyclerView.Adapter<Income_adapter.IncomeHolder>(){

    private lateinit var mListner: onItemClickListner

    interface onItemClickListner{
        fun  onItemClick(position: Int)
    }

    fun setOnItemClickListner(listner:onItemClickListner){

        mListner = listner
    }




    class IncomeHolder(incomeview:View,listner: onItemClickListner):RecyclerView.ViewHolder(incomeview){

        val iname: TextView = incomeview.findViewById(R.id.name)
        val iamont: TextView = incomeview.findViewById(R.id.amount)
        val Itype: TextView = incomeview.findViewById(R.id.lDate)
        val idate: TextView = incomeview.findViewById(R.id.setDate)

        init{

            incomeview.setOnClickListener{
                listner.onItemClick(adapterPosition)
            }


        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeHolder {

        var incomeview = LayoutInflater.from(parent.context).inflate(R.layout.display_income_layout,parent,false)
        return IncomeHolder(incomeview,mListner)

    }

    override fun getItemCount(): Int {
        return incomelist.size
    }

    override fun onBindViewHolder(holder: IncomeHolder, position: Int) {
        val currentincome = incomelist[position]


        holder.iname.setText(currentincome.name.toString())
        holder.iamont.setText(currentincome.amount.toString())
        holder.Itype.setText(currentincome.category.toString())
        holder.idate.setText(currentincome.settle_date.toString())

    }


}