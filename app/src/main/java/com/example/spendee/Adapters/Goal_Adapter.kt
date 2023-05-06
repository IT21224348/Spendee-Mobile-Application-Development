package com.example.spendee.Adapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.spendee.DataClass.Goal_model
import com.example.spendee.R

class Goal_Adapter(private  var goalist:ArrayList<Goal_model>):RecyclerView.Adapter<Goal_Adapter.GoalHolder>(){

    private lateinit var mListener:onItemClickListner

    interface  onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(listner:onItemClickListner){
        mListener = listner
    }

    class GoalHolder(goalview: View,listner:onItemClickListner):RecyclerView.ViewHolder(goalview){

        val Tname: TextView = goalview.findViewById(R.id.name)
        val Tdate: TextView = goalview.findViewById(R.id.amount)
        val Tamonut: TextView = goalview.findViewById(R.id.lDate)
        val Camonut: TextView = goalview.findViewById(R.id.setDate)

        init {
            goalview.setOnClickListener{

                listner.onItemClick(adapterPosition)

            }


        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalHolder {
        val goalview = LayoutInflater.from(parent.context).inflate(R.layout.display_target,parent,false)
        return GoalHolder(goalview,mListener)
    }

    override fun getItemCount(): Int {
        return goalist.size
    }

    override fun onBindViewHolder(holder: GoalHolder, position: Int) {
        val currentloan = goalist[position]
        holder.Tname.setText(currentloan.name.toString())
        holder.Tdate.setText(currentloan.date.toString())
        holder.Tamonut.setText(currentloan.target.toString())
        holder.Camonut.setText(currentloan.balance.toString())
    }


}

