package com.merqueo.co.core

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load

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
    textview.text = if (value) {
        "Si"
    } else {
        "No"
    }
}


private fun fullPosterUrl(movie: String): String {
    return "${BuildConfig.IMAGES_URL}${movie}"
}

private var isPlaceHolder: Boolean = false

@BindingAdapter("bindingImageView")
fun bindingImageView(imageview: ImageView, value: String) {
    fullPosterUrl(value).takeIf {
        !isPlaceHolder
    }?.let { imageUrl ->
        imageview.load(imageUrl) {
            placeholder(R.drawable.ic_cloud_off_black_24dp)
            target(onStart = {
            }, onSuccess = {
                imageview.setImageDrawable(it)
            }, onError = {
                imageview.setImageResource(R.drawable.ic_cloud_off_black_24dp)
            })
        }
    }
}

