package com.merqueo.co.merqueoprueba.view

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.merqueo.co.merqueoprueba.R
import com.merqueo.co.merqueoprueba.presentation.activity.SplashActivity
import com.merqueo.co.merqueoprueba.view.pagepattern.MoviePage
import com.merqueo.co.merqueoprueba.view.pagepattern.Page
import com.merqueo.co.merqueoprueba.view.pagepattern.PageUtils
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@InternalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class AddRemoveTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(SplashActivity::class.java)

    private lateinit var pageUtils: PageUtils

    @Before
    fun initPageUtils() {
        pageUtils = PageUtils()
    }

    fun startActivity() {
        activityRule.scenario
    }


    @Test
    fun addRemoveOnStore() {
        startActivity()
        pageUtils.sleep(5)
        Page.on<MoviePage>().clickToActionMovie(0, R.id.button_add)
        pageUtils.sleep(2)
        Page.on<MoviePage>().clickToActionMovie(0, R.id.button_delete)
        pageUtils.sleep(2)
    }


    @Test
    fun addRemoveAllOnStore() {
        startActivity()
        pageUtils.sleep(5)
        Page.on<MoviePage>().clickToActionMovie(0, R.id.button_add)
        pageUtils.sleep(2)
        Page.on<MoviePage>().clickOnMenu()
    }




}