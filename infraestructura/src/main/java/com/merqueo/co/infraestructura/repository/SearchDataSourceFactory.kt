package com.merqueo.co.infraestructura.repository

import androidx.paging.DataSource
import com.merqueo.co.core.typealiases.AnyResultMutableLiveData
import com.merqueo.co.infraestructura.network.api.IMovieApi
import com.merqueo.co.infraestructura.repository.remote.SearchRemoteRepository
import com.merqueo.co.models.dto.MovieDto

class SearchDataSourceFactory(
    private val movieApi: IMovieApi,
    private val query: String,
    private val resultLiveData: AnyResultMutableLiveData
) : DataSource.Factory<Int, MovieDto>() {
    override fun create(): DataSource<Int, MovieDto> {
        return SearchRemoteRepository(
            movieApi,
            query,
            resultLiveData
        )
    }
}