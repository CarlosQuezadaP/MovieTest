package com.merqueo.co.merqueoprueba.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.merqueo.co.merqueoprueba.R
import com.merqueo.co.merqueoprueba.databinding.ShoppingItemLayoutBinding
import com.merqueo.co.usecases.BuildConfig
import com.merqueo.co.usecases.presentacion.hide
import com.merqueo.co.usecases.presentacion.show

class MovieViewHolder(
    private val itemViewBinding: ShoppingItemLayoutBinding
) :
    RecyclerView.ViewHolder(itemViewBinding.root) {

    private var isPlaceHolder: Boolean = false

    fun bindTo(movie: com.merqueo.co.domain.models.MovieItemDomain) {

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

    private fun fullPosterUrl(movie: com.merqueo.co.domain.models.MovieItemDomain): String {
        return "${BuildConfig.IMAGES_URL}${movie.posterPath}"
    }


    private fun setVoteAverage(item: com.merqueo.co.domain.models.MovieItemDomain) {
        if (item.voteAverage > 0.0) {
            itemViewBinding.voteAverageTextView.show()
            itemViewBinding.voteAverageTextView.text = item.voteAverage.toString()
        } else {
            itemViewBinding.voteAverageTextView.hide()
        }
    }

}