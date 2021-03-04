package com.merqueo.co.core.util

import com.merqueo.co.models.ui.MovieItemDomain


interface AddRemoveListener {
    fun onItemClickOnButton(movieItemDomain: MovieItemDomain, type: Boolean)
}