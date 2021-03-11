package com.merqueo.co.data.di

import com.merqueo.co.data.remoteSource.IMoviesRemoteSource
import com.merqueo.co.data.remoteSource.MoviesRemoteSource
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.dsl.module

@InternalCoroutinesApi
val moduleRemoteSource = module {
    single<IMoviesRemoteSource> { MoviesRemoteSource(get()) }
}