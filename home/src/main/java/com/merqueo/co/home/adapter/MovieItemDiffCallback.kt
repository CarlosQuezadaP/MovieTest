package com.merqueo.co.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.merqueo.co.models.ui.MovieItemUI

class MovieItemDiffCallback : DiffUtil.ItemCallback<MovieItemUI>() {

    override fun areItemsTheSame(oldItem: MovieItemUI, newItem: MovieItemUI): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieItemUI, newItem: MovieItemUI): Boolean =
        oldItem == newItem
}