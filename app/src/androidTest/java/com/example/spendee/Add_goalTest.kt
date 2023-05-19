package com.example.spendee

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class Add_goalTest {

    private lateinit var scenario: ActivityScenario<*>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(Add_Goal::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testaddgoal() {
        val name = "Kavindu"
        val amount = "100" // Use a string instead of an integer for typeText()
        val date = "3/5/2021"
        val settledate = "8/5/2022"

        Espresso.onView(ViewMatchers.withId(R.id.loanName)).perform(ViewActions.typeText(name))
        Espresso.onView(ViewMatchers.withId(R.id.loanAmount)).perform(ViewActions.typeText(date ))
        Espresso.onView(ViewMatchers.withId(R.id.loanDate)).perform(ViewActions.typeText(amount))

        Espresso.closeSoftKeyboard()

        // Wait for the 'loanSettle1' view to be displayed before clicking
        Espresso.onView(ViewMatchers.withId(R.id.loanSettle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())


        // Perform the remaining actions and assertions
        Espresso.onView(ViewMatchers.withId(R.id.loanSettle))
            .perform(ViewActions.typeText(settledate))
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.AddLoan)).perform(ViewActions.click())


    }





}






