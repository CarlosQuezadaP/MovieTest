package com.ceiba.adn_csh.view

import android.view.View
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.merqueo.co.merqueoprueba.ObjectUtils
import com.merqueo.co.merqueoprueba.R
import com.merqueo.co.merqueoprueba.presentation.activity.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class HomeTest {

    private lateinit var objectUtils: ObjectUtils

    private lateinit var messageSuccesfulOnStore: String

    @Rule
    @JvmField
    val activityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)
    private lateinit var decorView: View


    @Before
    fun initialization() {
        objectUtils = ObjectUtils()
        activityScenarioRule.scenario.onActivity { activity ->
            decorView = activity.window.decorView
        }

        messageSuccesfulOnStore = "se realizo el cambio de estado."
    }

    @Throws(InterruptedException::class)
    @Test
    fun addMovieOnStore() {
        ObjectUtils.openNavHostFragment()
        ObjectUtils.sleep(3)
        ObjectUtils.clickItemRecyclerList(R.id.recyclerview, 0, R.id.button_add)
        ObjectUtils.sleep(1)
        ObjectUtils.verifyDisplayToastAllways(messageSuccesfulOnStore, decorView)
    }

    @Throws(InterruptedException::class)
    @Test
    fun removeMovieOnStore() {
        ObjectUtils.openNavHostFragment()
        ObjectUtils.sleep(3)
        ObjectUtils.clickItemRecyclerList(R.id.recyclerview, 0, R.id.button_delete)
        ObjectUtils.sleep(1)
        ObjectUtils.verifyDisplayToastAllways(messageSuccesfulOnStore, decorView)
    }


}