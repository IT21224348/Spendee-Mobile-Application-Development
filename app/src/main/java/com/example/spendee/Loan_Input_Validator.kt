package com.example.spendee.Validotors

object loan_Input_Validator {

    fun validinput(name: String, amount: Double , date:String, settle_date : String):Boolean{

         return  !(name.isEmpty() || amount <= 0 || date.isEmpty() || settle_date.isEmpty())

    }



}