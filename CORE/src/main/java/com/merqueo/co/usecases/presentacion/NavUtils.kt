package com.merqueo.co.usecases.presentacion

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.merqueo.co.usecases.R

fun createDefaultNavOptions(destination: Int) = NavOptions.Builder()
    .setLaunchSingleTop(false)
    .setPopUpTo(destination, false)
    .setEnterAnim(R.anim.nav_default_enter_anim)
    .setExitAnim(R.anim.nav_default_exit_anim)
    .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
    .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
    .build()

fun NavController.navigateUriWithDefaultOptions(
    uri: Uri
) {
    this.navigate(uri, createDefaultNavOptions(this.currentDestination?.id ?: -1))
}