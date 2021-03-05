package com.merqueo.co.merqueoprueba.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.merqueo.co.merqueoprueba.R
import com.merqueo.co.merqueoprueba.databinding.ShoppingItemLayoutBinding
import com.merqueo.co.domain.models.MovieItemDomain

class StoreAdapter :
    ListAdapter<com.merqueo.co.domain.models.MovieItemDomain, MovieViewHolder>(MovieItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MovieViewHolder {
        val layout = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.shopping_item_layout, parent, false
        ) as ShoppingItemLayoutBinding

        return MovieViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}