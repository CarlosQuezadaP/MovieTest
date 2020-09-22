package com.merqueo.co.models.dto

import androidx.annotation.Keep
import com.merqueo.co.core.base.IConvertableTo
import com.merqueo.co.models.entities.GenreEntity

@Keep
data class GenreDto(
    val id: Int?,
    val name: String?
) : IConvertableTo<GenreEntity> {
    override fun convertTo(): GenreEntity? {
        return if (this.id != null && !this.name.isNullOrEmpty()) {
            GenreEntity(this.id, this.name)
        } else null
    }
}