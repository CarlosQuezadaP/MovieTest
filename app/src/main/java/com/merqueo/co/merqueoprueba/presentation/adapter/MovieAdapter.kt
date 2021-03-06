package com.merqueo.co.merqueoprueba.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.merqueoprueba.R
import com.merqueo.co.merqueoprueba.databinding.ItemMovieLayoutBinding
import com.merqueo.co.merqueoprueba.handlers.AddRemoveListener
import com.merqueo.co.merqueoprueba.handlers.ClickListener

class MovieAdapter(
    private val addRemoveListener: AddRemoveListener,
    private val clickListener: ClickListener
) :
    ListAdapter<MovieItemDomain, MovieViewHolder1>(MovieItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MovieViewHolder1 {
        val layout = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_layout, parent, false
        ) as ItemMovieLayoutBinding

        return MovieViewHolder1(layout, addRemoveListener, clickListener)
    }

    override fun onBindViewHolder(holder1: MovieViewHolder1, position: Int) {
        holder1.bindTo(getItem(position))
    }

    override fun submitList(list: List<MovieItemDomain>?) {
        super.submitList(list?.apply { ArrayList(this) })
    }
}