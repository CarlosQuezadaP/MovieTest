package com.merqueo.co.merqueoprueba.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.merqueo.co.merqueoprueba.R


fun View.hide(gone: Boolean = true) {
    visibility = if (gone) View.GONE else View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}


fun Fragment.setExitToFullScreenTransition() {
    exitTransition =
        TransitionInflater.from(context)
            .inflateTransition(R.transition.list_exit_transition)
}


fun Fragment.setReturnFromFullScreenTransition() {
    reenterTransition =
        TransitionInflater.from(context)
            .inflateTransition(R.transition.list_exit_transition)
}








