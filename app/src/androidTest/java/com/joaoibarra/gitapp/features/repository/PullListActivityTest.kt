package com.joaoibarra.gitapp.features.repository

import android.app.Instrumentation
import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers.hasAction
import android.support.test.espresso.intent.matcher.IntentMatchers.hasData
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import com.joaoibarra.gitapp.Constants
import com.joaoibarra.gitapp.R
import org.hamcrest.core.AllOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PullListActivityTest {
    @get:Rule
    val pullsActivityRule = IntentsTestRule<PullListActivity>(PullListActivity::class.java, false, false)

    @Before
    fun setUp() {
        val intent = Intent()
        intent.putExtra(Constants.USER, Constants.USERTEST)
        intent.putExtra(Constants.REPO, Constants.REPOTEST)
        pullsActivityRule.launchActivity(intent)
    }

    @After
    fun unregisterIdlingResource() {
    }

    @Test
    fun checkRecyclerView_whenload_shouldIsDisplayed() {
        Espresso.onView(withId(R.id.recyclerPulls))
                .perform(click())
                .check(ViewAssertions.matches(isDisplayed()))
    }
//
//    @Test
//    fun checkRecyclerView_whenClicked_shouldOpenBrowser() {
//        // TODO isso é péssimo
//        Thread.sleep(3000)
//
//        Espresso.onView(withId(R.id.recyclerPulls))
//                .check(ViewAssertions.matches(isDisplayed()))
//                .perform(
//                        RecyclerViewActions.actionOnItem<RepositoryAdapter.ItemViewHolder>(
//                                hasDescendant(withText(Constants.PULLTITLE)), click()))
//
//        val expectedIntent = AllOf.allOf(hasAction(Intent.ACTION_VIEW), hasData(Constants.URLTEST))
//        intending(expectedIntent).respondWith(Instrumentation.ActivityResult(0, null))
//
//        intended(expectedIntent)
//    }
}