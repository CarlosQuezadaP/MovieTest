package com.merqueo.co.usecases.util

import com.merqueo.co.models.ui.MovieItemDomain

interface ClickListener {
    fun onClick(movieItemDomain: MovieItemDomain)
}
