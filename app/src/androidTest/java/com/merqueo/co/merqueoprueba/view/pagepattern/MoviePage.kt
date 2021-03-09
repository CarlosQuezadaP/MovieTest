package com.merqueo.co.merqueoprueba.view.pagepattern

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.merqueo.co.merqueoprueba.R
import com.merqueo.co.merqueoprueba.presentation.adapter.MovieViewHolder1

class MoviePage : Page() {

    @IdRes
    val rvEvents: Int = R.id.recyclerviewMovies

    @IdRes
    val menuItemShop: Int = R.id.shopFragment2


    fun clickToActionMovie(position: Int, idView: Int): Page {
        Espresso.onView(ViewMatchers.withId(rvEvents))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<MovieViewHolder1>(
                    position,
                    clickOnViewChild(idView)
                )
            )
        return this
    }

    fun clickOnMenu(): Page {
        Espresso.onView(ViewMatchers.withId(menuItemShop)).perform(click())
        return this
    }


    fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) =
            click().perform(uiController, view.findViewById(viewId))
    }
}