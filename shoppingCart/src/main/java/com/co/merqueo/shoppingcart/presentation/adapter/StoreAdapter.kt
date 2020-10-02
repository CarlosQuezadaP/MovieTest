package com.co.merqueo.shoppingcart.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.co.merqueo.shoppingcart.R
import com.co.merqueo.shoppingcart.databinding.ShoppingItemLayoutBinding
import com.merqueo.co.models.ui.MovieItemDomain

class StoreAdapter :
    ListAdapter<MovieItemDomain, MovieViewHolder>(MovieItemDiffCallback()) {

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