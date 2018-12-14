package com.joaoibarra.gitapp.features.repository

import android.app.Instrumentation
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent

import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.joaoibarra.gitapp.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class RepositoryListActivityTest {

    @get:Rule
    val repositoriesActivityRule = ActivityTestRule<RepositoryListActivity>(RepositoryListActivity::class.java, false, false)

    @Before
    fun setUp() {
        repositoriesActivityRule.launchActivity(Intent())
    }

    @After
    fun unregisterIdlingResource() {
        repositoriesActivityRule.finishActivity()
    }

    @Test
    fun checkRecyclerView_whenClicked_shouldIsDisplayed() {
        onView(withId(R.id.recyclerRepositories))
                .perform(click())
                .check(matches(isDisplayed()))
    }

    @Test
    fun checkRecyclerView_whenClicked_shouldRedirectToNextActivity() {
        // TODO isso é péssimo
        Thread.sleep(3000)

        // Intents.intending()

//        val instrumentation = InstrumentationRegistry.getInstrumentation()
//        instrumentation.waitForIdleSync()

        onView(withId(R.id.recyclerRepositories))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition<RepositoryAdapter.ItemViewHolder>(0, click()))

        intended(hasComponent(PullListActivity::class.java.name))
    }
//
//    @Test
//    fun checkRecyclerView_whenClickedInSecondPage_shouldRedirectToNextActivity() {
//        // TODO isso é péssimo
//        // Thread.sleep(3000)
//
//        onView(withId(R.id.recyclerRepositories))
//                .check(matches(isDisplayed()))
//                .perform(
//                        RecyclerViewActions.actionOnItemAtPosition<RepositoryAdapter.ItemViewHolder>(40, click()))
//
//        intended(hasComponent(PullListActivity::class.java.name))
//    }
}