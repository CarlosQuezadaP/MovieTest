package com.merqueo.co.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.merqueo.co.core.BuildConfig
import com.merqueo.co.core.hide
import com.merqueo.co.core.show
import com.merqueo.co.home.R
import com.merqueo.co.models.ui.MovieItemUI
import kotlinx.android.synthetic.main.item_movie_layout.view.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var isPlaceHolder: Boolean = false

    fun bindTo(movie: MovieItemUI) {
        itemView.titleTextView.text = movie.title
        itemView.releaseTextView.text = movie.releaseDate
        itemView.overviewTextView.text = movie.overview
        fullPosterUrl(movie).takeIf {
            !isPlaceHolder
        }?.let { imageUrl ->
            itemView.movieImageView.load(imageUrl) {
                placeholder(R.drawable.ic_cloud_off_black_24dp)
                target(onStart = {
                    itemView.imageProgressBar.show()
                }, onSuccess = {
                    itemView.movieImageView.setImageDrawable(it)
                    itemView.imageProgressBar.hide(true)
                }, onError = {
                    itemView.movieImageView.setImageResource(R.drawable.ic_cloud_off_black_24dp)
                    itemView.imageProgressBar.hide(true)
                })
            }
        }


    }

    fun fullPosterUrl(movie: MovieItemUI): String {
        return "${BuildConfig.IMAGES_URL}${movie.posterPath}"
    }


}