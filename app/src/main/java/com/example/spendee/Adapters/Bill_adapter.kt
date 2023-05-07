package com.example.spendee.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spendee.DataClass.Bill_model
import com.example.spendee.R

class Bill_adapter(private var billList:ArrayList<Bill_model>):RecyclerView.Adapter<Bill_adapter.BillHolder>() {

    private lateinit var mListener:onItemClickListner

    interface  onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(listner:onItemClickListner){

        mListener = listner
    }


    class BillHolder(billview: View,listner:onItemClickListner):RecyclerView.ViewHolder(billview){

        val lonaName: TextView = billview.findViewById(R.id.name)
        val amountl: TextView = billview.findViewById(R.id.amount)
        val ldate: TextView = billview.findViewById(R.id.lDate)


        init{
            billview.setOnClickListener{
                listner.onItemClick(adapterPosition)

            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillHolder {
        val billview = LayoutInflater.from(parent.context).inflate(R.layout.display_bill_layout,parent,false)
        return  BillHolder(billview,mListener)
    }

    override fun getItemCount(): Int {
        return  billList.size
    }

    override fun onBindViewHolder(holder: BillHolder, position: Int) {
        val currentloan = billList[position]
        holder.lonaName.setText(currentloan.name.toString())
        holder.amountl.setText(currentloan.target.toString())
        holder.ldate.setText(currentloan.date.toString())
    }


}