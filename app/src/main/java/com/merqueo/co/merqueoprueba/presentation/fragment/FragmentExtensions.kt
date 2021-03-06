package com.merqueo.co.merqueoprueba.presentation.fragment

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(message: String?, duration: Int) {
    message?.let {
        Toast.makeText(activity, it, duration).show()
    }
}
