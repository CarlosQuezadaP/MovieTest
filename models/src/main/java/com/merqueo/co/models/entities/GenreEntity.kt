package com.merqueo.co.models.entities

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.merqueo.co.core.base.IConvertableTo
import com.merqueo.co.models.ui.GenreItemUI

@Keep
@Entity
data class GenreEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val images: MutableList<String> = mutableListOf()
) : IConvertableTo<GenreItemUI> {
    override fun convertTo(): GenreItemUI {
        return GenreItemUI(id = this.id, name = this.name, images = this.images)
    }
}