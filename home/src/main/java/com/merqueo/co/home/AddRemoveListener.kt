package com.merqueo.co.home

import com.merqueo.co.models.ui.MovieItemDomain

interface AddRemoveListener {
    fun onItemClickOnButton(movieItemDomain: MovieItemDomain, type: Boolean)
}