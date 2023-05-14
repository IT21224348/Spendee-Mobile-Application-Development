package com.example.spendee

import com.example.spendee.Validotors.loan_Input_Validator
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class loan_Input_ValidatorTest{


       @Test
       fun whenLoanInputIsValid(){

           val name = "Ravindu"
           val amount = 1000.00
           val date  = "10/05/2023"
           val  settle_date = "14/08/2023"

            val result = loan_Input_Validator.validinput(name,amount,date,settle_date)
            assertThat(result).isEqualTo(true)

       }

    @Test
    fun whenLoanInputIsInValid(){

        val name = ""
        val amount = 0.00
        val date  = ""
        val  settle_date = ""

        val result = loan_Input_Validator.validinput(name,amount,date,settle_date)
        assertThat(result).isEqualTo(false)

    }



}