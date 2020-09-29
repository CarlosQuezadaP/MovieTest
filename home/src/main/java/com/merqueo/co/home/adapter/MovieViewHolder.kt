package com.merqueo.co.home.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.merqueo.co.core.BuildConfig
import com.merqueo.co.core.hide
import com.merqueo.co.core.show
import com.merqueo.co.home.ClickListener
import com.merqueo.co.home.R
import com.merqueo.co.home.databinding.ItemMovieLayoutBinding
import com.merqueo.co.models.ui.MovieItemDomain
import kotlinx.android.synthetic.main.item_movie_layout.view.*

class MovieViewHolder(
    private val itemViewBinding: ItemMovieLayoutBinding,
    private val clickListener: ClickListener
) :
    RecyclerView.ViewHolder(itemViewBinding.root) {

    private var isPlaceHolder: Boolean = false

    fun bindTo(movie: MovieItemDomain) {

        val row = movie
        itemViewBinding.movie = row
        itemViewBinding.movieClickInterface = clickListener

        setVoteAverage(movie)
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

    private fun fullPosterUrl(movie: MovieItemDomain): String {
        return "${BuildConfig.IMAGES_URL}${movie.posterPath}"
    }


    private fun setVoteAverage(item: MovieItemDomain) {
        if (item.voteAverage > 0.0) {
            itemView.voteAverageTextView.show()
            itemView.voteAverageTextView.text = item.voteAverage.toString()
        } else {
            itemView.voteAverageTextView.hide()
        }
    }

}