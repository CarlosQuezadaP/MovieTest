package com.merqueo.co.home

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("bindingDoubleToText")
fun bindingDoubleToText(textview: TextView, double: Double) {
    textview.text = double.toString()
}

@BindingAdapter("bindingIntToText")
fun bindingIntToText(textview: TextView, value: Int) {
    textview.text = value.toString()
}
