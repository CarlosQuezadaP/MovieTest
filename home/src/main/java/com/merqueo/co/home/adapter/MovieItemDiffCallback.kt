package com.merqueo.co.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.merqueo.co.models.dto.upcoming.MovieDto

class MovieItemDiffCallback : DiffUtil.ItemCallback<MovieDto>() {

    override fun areItemsTheSame(oldItem: MovieDto, newItem: MovieDto): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieDto, newItem: MovieDto): Boolean =
        oldItem == newItem
}