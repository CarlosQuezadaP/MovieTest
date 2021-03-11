package com.merqueo.co.merqueoprueba.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.google.android.material.button.MaterialButton
import com.merqueo.co.merqueoprueba.R

@BindingAdapter("bindingDrawable")
fun bindingDrawable(button: MaterialButton, color: Int) {
    button.setBackgroundColor(color)
}

@BindingAdapter("bindingDoubleToText")
fun bindingDoubleToText(textview: TextView, double: Double) {
    textview.text = double.toString()
}

@BindingAdapter("bindingIntToText")
fun bindingIntToText(textview: TextView, value: Int) {
    textview.text = value.toString()
}


@BindingAdapter("bindingBooleanToText")
fun bindingBooleanToText(textview: TextView, value: Boolean) {
    textview.text = if (value) "Si" else "No"
}


@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun setImageUrl(imageView: ImageView, url: String?, placeHolder: Drawable?) {
    imageView.load(url?.apply { fullPosterUrl(this) }) {
        placeholder(placeHolder)
        target(onStart = {

        }, onSuccess = {
            imageView.setImageDrawable(it)
        }, onError = {
            imageView.setImageResource(R.drawable.ic_cloud_off_black_24dp)
        })
    }
}

private fun fullPosterUrl(posterPath: String): String {
    return "${"https://image.tmdb.org/t/p/original/"}${posterPath}"
}
