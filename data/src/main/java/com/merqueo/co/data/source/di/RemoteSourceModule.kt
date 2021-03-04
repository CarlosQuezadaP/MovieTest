package com.merqueo.co.data.source.di

import com.merqueo.co.data.source.remote.IMoviesRemoteSource
import com.merqueo.co.data.source.remote.MoviesRemoteSource
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.dsl.module

@InternalCoroutinesApi
val moduleRemoteSource = module {
    single<IMoviesRemoteSource> { MoviesRemoteSource(get()) }
}