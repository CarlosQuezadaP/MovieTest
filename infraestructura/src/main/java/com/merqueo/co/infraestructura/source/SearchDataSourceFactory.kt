package com.merqueo.co.infraestructura.source

import androidx.paging.DataSource
import com.merqueo.co.core.typealiases.AnyResultMutableLiveData
import com.merqueo.co.infraestructura.network.api.IMovieApi
import com.merqueo.co.infraestructura.source.remote.SearchRemoteSource
import com.merqueo.co.models.dto.MovieDto

class SearchDataSourceFactory(
    private val movieApi: IMovieApi,
    private val query: String,
    private val resultLiveData: AnyResultMutableLiveData
) : DataSource.Factory<Int, MovieDto>() {
    override fun create(): DataSource<Int, MovieDto> {
        return SearchRemoteSource(
            movieApi,
            query,
            resultLiveData
        )
    }
}