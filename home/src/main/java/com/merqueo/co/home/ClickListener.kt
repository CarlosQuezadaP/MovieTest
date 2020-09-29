package com.merqueo.co.home

import com.merqueo.co.models.ui.MovieItemDomain

interface ClickListener {
    fun onItemClick(movieItemDomain: MovieItemDomain, type: Boolean)
}