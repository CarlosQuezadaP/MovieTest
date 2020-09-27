package com.merqueo.co.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.merqueo.co.home.R
import com.merqueo.co.models.ui.MovieItemUI

class MovieAdapter : ListAdapter<MovieItemUI, MovieViewHolder>(MovieItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}