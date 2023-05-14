package com.example.spendee



import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith






@RunWith(AndroidJUnit4::class)
@LargeTest
class Add_LoanTest {

    private lateinit var scenario: ActivityScenario<*>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(Add_Loan::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testaddloan() {
        val name = "Kavindu"
        val amount = "100" // Use a string instead of an integer for typeText()
        val date = "3/5/2021"
        val settledate = "8/5/2022"

        onView(withId(R.id.loanName1)).perform(typeText(name))
        onView(withId(R.id.loanAmount1)).perform(typeText(amount))
        onView(withId(R.id.loanDate1)).perform(typeText(date))

        Espresso.closeSoftKeyboard()

        // Wait for the 'loanSettle1' view to be displayed before clicking
        onView(withId(R.id.loanSettle1))
            .check(matches(isDisplayed()))
            .perform(click())


        // Perform the remaining actions and assertions
        onView(withId(R.id.loanSettle1)).perform(typeText(settledate))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.AddLoan1)).perform(click())


    }
}
