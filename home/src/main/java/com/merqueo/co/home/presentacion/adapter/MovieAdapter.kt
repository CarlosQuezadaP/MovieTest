package com.merqueo.co.home.presentacion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.merqueo.co.core.util.AddRemoveListener
import com.merqueo.co.core.util.ClickListener
import com.merqueo.co.home.R
import com.merqueo.co.home.databinding.ItemMovieLayoutBinding
import com.merqueo.co.models.ui.MovieItemDomain

class MovieAdapter(
    private val addRemoveListener: AddRemoveListener,
    private val clickListener: ClickListener
) :
    ListAdapter<MovieItemDomain, MovieViewHolder>(MovieItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MovieViewHolder {
        val layout = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_layout, parent, false
        ) as ItemMovieLayoutBinding

        return MovieViewHolder(layout, addRemoveListener, clickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}