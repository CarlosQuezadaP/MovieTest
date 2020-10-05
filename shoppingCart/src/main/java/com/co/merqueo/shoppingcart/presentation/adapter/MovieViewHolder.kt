package com.co.merqueo.shoppingcart.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.co.merqueo.shoppingcart.R
import com.co.merqueo.shoppingcart.databinding.ShoppingItemLayoutBinding
import com.merqueo.co.core.BuildConfig
import com.merqueo.co.core.presentacion.hide
import com.merqueo.co.core.presentacion.show
import com.merqueo.co.models.ui.MovieItemDomain

class MovieViewHolder(
    private val itemViewBinding: ShoppingItemLayoutBinding
) :
    RecyclerView.ViewHolder(itemViewBinding.root) {

    private var isPlaceHolder: Boolean = false

    fun bindTo(movie: MovieItemDomain) {

        val row = movie
        itemViewBinding.movie = row

        setVoteAverage(movie)
        fullPosterUrl(movie).takeIf {
            !isPlaceHolder
        }?.let { imageUrl ->
            itemViewBinding.movieImageView.load(imageUrl) {
                placeholder(R.drawable.ic_cloud_off_black_24dp)
                target(onStart = {
                    itemViewBinding.imageProgressBar.show()
                }, onSuccess = {
                    itemViewBinding.movieImageView.setImageDrawable(it)
                    itemViewBinding.imageProgressBar.hide(true)
                }, onError = {
                    itemViewBinding.movieImageView.setImageResource(R.drawable.ic_cloud_off_black_24dp)
                    itemViewBinding.imageProgressBar.hide(true)
                })
            }
        }
    }

    private fun fullPosterUrl(movie: MovieItemDomain): String {
        return "${BuildConfig.IMAGES_URL}${movie.posterPath}"
    }


    private fun setVoteAverage(item: MovieItemDomain) {
        if (item.voteAverage > 0.0) {
            itemViewBinding.voteAverageTextView.show()
            itemViewBinding.voteAverageTextView.text = item.voteAverage.toString()
        } else {
            itemViewBinding.voteAverageTextView.hide()
        }
    }

}