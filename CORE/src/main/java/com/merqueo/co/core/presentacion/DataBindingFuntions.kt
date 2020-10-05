package com.merqueo.co.core.presentacion

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.merqueo.co.core.BuildConfig
import com.merqueo.co.core.R

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
    imageView.load(url?.let { fullPosterUrl(it) }) {
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
    return "${BuildConfig.IMAGES_URL}${posterPath}"
}


