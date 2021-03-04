package com.merqueo.co.usecases.util

import com.merqueo.co.models.ui.MovieItemDomain


interface AddRemoveListener {
    fun onItemClickOnButton(movieItemDomain: MovieItemDomain, type: Boolean)
}