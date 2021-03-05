package com.merqueo.co.usecases.util

import com.merqueo.co.domain.models.MovieItemDomain


interface AddRemoveListener {
    fun onItemClickOnButton(movieItemDomain: com.merqueo.co.domain.models.MovieItemDomain, type: Boolean)
}