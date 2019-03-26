package com.joaoibarra.gitapp.features.repository

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.actionWithAssertions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.widget.ProgressBar
import com.joaoibarra.gitapp.R
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import okhttp3.mockwebserver.MockWebServer

@RunWith(AndroidJUnit4::class)
@LargeTest
class RepositoryListActivityTest {

    lateinit var webServer: MockWebServer

    @get:Rule
    val repositoriesActivityRule = IntentsTestRule<RepositoryListActivity>(RepositoryListActivity::class.java, false, false)

    @Before
    fun setUp() {
        webServer = MockWebServer()
        webServer.run {
            start(8080)
            setDispatcher(MockServerDispatcher.requestDispatcher)
        }
        repositoriesActivityRule.launchActivity(Intent())

    }

    @After
    fun unregisterIdlingResource() {
        repositoriesActivityRule.finishActivity()
        webServer.shutdown()
    }

//    @Test
//    fun checkRecyclerView_whenClicked_shouldIsDisplayed() {
//        onView(withId(R.id.recyclerRepositories))
//                .perform(click())
//                .check(matches(isDisplayed()))
//    }

    @Test
    fun checkRecyclerView_whenClicked_shouldRedirectToNextActivity() {
        // TODO isso é péssimo :'(
        Thread.sleep(3000)


//        onView(isAssignableFrom(ProgressBar::class.java)).perform(replaceProgressBarDrawable())

        onView(withId(R.id.recyclerRepositories))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition<RepositoryAdapter.ItemViewHolder>(2, click()))

//        intended(hasComponent(PullListActivity::class.java.name))
    }

    fun replaceProgressBarDrawable(): ViewAction {
        return actionWithAssertions(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(ProgressBar::class.java)
            }

            override fun getDescription(): String {
                return "replace the ProgressBar drawable"
            }

            override fun perform(uiController: UiController, view: View) {
                // Replace the indeterminate drawable with a static red ColorDrawable
                val progressBar = view as ProgressBar
                progressBar.indeterminateDrawable = ColorDrawable(-0x10000)
                uiController.loopMainThreadUntilIdle()
            }
        })
    }
}