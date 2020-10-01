package com.merqueo.co.core.util

import com.merqueo.co.models.ui.MovieItemDomain

interface ClickListener {
    fun onClick(movieItemDomain: MovieItemDomain)
}
