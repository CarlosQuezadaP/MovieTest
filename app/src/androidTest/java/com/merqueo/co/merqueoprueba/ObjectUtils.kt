package com.merqueo.co.merqueoprueba

import android.view.View
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import org.hamcrest.Matcher
import org.hamcrest.Matchers


class ObjectUtils {

    companion object {
        @Throws(InterruptedException::class)
        fun sleep(seconds: Long) {
            Thread.sleep(seconds * Constants.TIME_OUT)
        }

        fun click(id: Int) {
            onView(ViewMatchers.withId(id)).perform(ViewActions.click())
        }

        fun click(text: String) {
            onView(ViewMatchers.withText(text)).perform(ViewActions.click())
        }

        fun verifyDisplayToastAllways(text: String, decorView: View) {
            onView(ViewMatchers.withText(text))
                .inRoot(RootMatchers.withDecorView(Matchers.not(decorView)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        fun verifyDisplayToastCondition(textSuccess: String, textError: String, decorView: View) {
            if (!onView(ViewMatchers.withText(textSuccess)).inRoot(
                    RootMatchers.withDecorView(
                        Matchers.not(decorView)
                    )
                ).isDisplayed()
            ) {
                onView(ViewMatchers.withText(textError))
                    .inRoot(RootMatchers.withDecorView(Matchers.not(decorView)))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            }
        }

        fun verifyDisplayComponent(id: Int) {
            onView(ViewMatchers.withId(id)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        fun ViewInteraction.isDisplayed(): Boolean {
            return try {
                check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                true
            } catch (e: NoMatchingViewException) {
                false
            }
        }

        private fun clickButtonViewWithId(id: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? {
                    return null
                }

                override fun getDescription(): String {
                    return "Click on a child view with specified id."
                }

                override fun perform(uiController: UiController, view: View) {
                    val v = view.findViewById<Button>(id)
                    v.performClick()
                }
            }
        }

        private fun clickCardViewWithId(id: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? {
                    return null
                }

                override fun getDescription(): String {
                    return "Click on a child view with specified id."
                }

                override fun perform(uiController: UiController, view: View) {
                    val v = view.findViewById<CardView>(id)
                    v.performClick()
                }
            }
        }

        fun openNavHostFragment() {
            onView(ViewMatchers.withId(R.id.nav_graph))
        }


        fun pressBack() {
            onView(isRoot()).perform(ViewActions.pressBack())
        }

        fun clickItemRecyclerList(id: Int, position: Int, componentIdClick: Int) {
            onView(ViewMatchers.withId(id))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        position,
                        clickButtonViewWithId(componentIdClick)
                    )
                )
        }
    }
}