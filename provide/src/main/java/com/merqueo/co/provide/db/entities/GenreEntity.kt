package com.merqueo.co.provide.db.entities

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity
data class GenreEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val images: MutableList<String> = mutableListOf()
)