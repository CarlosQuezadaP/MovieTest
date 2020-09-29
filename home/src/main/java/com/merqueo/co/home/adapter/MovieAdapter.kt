package com.merqueo.co.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.merqueo.co.home.ClickListener
import com.merqueo.co.home.R
import com.merqueo.co.home.databinding.ItemMovieLayoutBinding
import com.merqueo.co.models.ui.MovieItemDomain

class MovieAdapter(private val clickListener: ClickListener) :
    ListAdapter<MovieItemDomain, RecyclerView.ViewHolder>(MovieItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MovieViewHolder {
        val layout: ItemMovieLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_layout, parent, false
        )

        return MovieViewHolder(layout, clickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieViewHolder).bindTo(getItem(position))
    }
}