package com.merqueo.co.data.anticorruption

import com.merqueo.co.data.db.entities.MovieEntity
import com.merqueo.co.domain.models.MovieItemDomain

interface IConverter {
    fun convertEntityToDomain(movieEntity: MovieEntity): MovieItemDomain
    fun convertDomainToEntity(movieItemDomain: MovieItemDomain): MovieEntity
}