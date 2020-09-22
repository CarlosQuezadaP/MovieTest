package com.merqueo.co.models.ui

import androidx.annotation.Keep

@Keep
data class GenreItemUI(
    val id: Int,
    val name: String,
    val images: List<String>
)