package com.merqueo.co.merqueoprueba.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.merqueo.co.CORE.BuildConfig
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.merqueoprueba.R
import com.merqueo.co.merqueoprueba.databinding.ItemMovieLayoutBinding
import com.merqueo.co.merqueoprueba.handlers.AddRemoveListener
import com.merqueo.co.merqueoprueba.handlers.ClickListener
import com.merqueo.co.merqueoprueba.utils.hide
import com.merqueo.co.merqueoprueba.utils.show

class MovieViewHolder1(
    private val itemViewBinding: ItemMovieLayoutBinding,
    private val addRemoveListener: AddRemoveListener,
    private val clickListener: ClickListener,
) :
    RecyclerView.ViewHolder(itemViewBinding.root) {

    private var isPlaceHolder: Boolean = false

    fun bindTo(movie: MovieItemDomain) {

        val row = movie
        itemViewBinding.movie = row
        itemViewBinding.movieClickInterface = addRemoveListener
        itemViewBinding.clickInterface = clickListener

        setVoteAverage(movie)


        fullPosterUrl(movie).takeIf {
            !isPlaceHolder
        }?.apply {
            itemViewBinding.movieImageView.load(this) {
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