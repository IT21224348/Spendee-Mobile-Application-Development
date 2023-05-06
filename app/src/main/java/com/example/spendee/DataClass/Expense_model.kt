package com.example.spendee.DataClass

data class Expense_model(val name:String? = null,
                         val amount:Double= 0.00,
                         val  category: String? = null,
                         val settle_date: String? = null)
