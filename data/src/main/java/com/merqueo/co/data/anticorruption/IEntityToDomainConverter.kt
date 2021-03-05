package com.merqueo.co.data.anticorruption

import com.merqueo.co.data.source.entities.MovieEntity
import com.merqueo.co.domain.models.MovieItemDomain

interface IEntityToDomainConverter {
    fun convertEntityToDomain(movieEntity: MovieEntity): MovieItemDomain
    fun convertDomainToEntity(movieItemDomain: MovieItemDomain): MovieEntity
}