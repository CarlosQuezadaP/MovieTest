package com.merqueo.co.merqueoprueba.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.merqueo.co.domain.models.MovieItemDomain

class MovieItemDiffCallback : DiffUtil.ItemCallback<com.merqueo.co.domain.models.MovieItemDomain>() {

    override fun areItemsTheSame(oldItem: com.merqueo.co.domain.models.MovieItemDomain, newItem: com.merqueo.co.domain.models.MovieItemDomain): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: com.merqueo.co.domain.models.MovieItemDomain, newItem: com.merqueo.co.domain.models.MovieItemDomain): Boolean =
        oldItem == newItem
}