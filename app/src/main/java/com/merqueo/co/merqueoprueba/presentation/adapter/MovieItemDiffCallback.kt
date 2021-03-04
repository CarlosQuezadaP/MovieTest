package com.merqueo.co.merqueoprueba.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.merqueo.co.models.ui.MovieItemDomain

class MovieItemDiffCallback : DiffUtil.ItemCallback<MovieItemDomain>() {

    override fun areItemsTheSame(oldItem: MovieItemDomain, newItem: MovieItemDomain): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieItemDomain, newItem: MovieItemDomain): Boolean =
        oldItem == newItem
}