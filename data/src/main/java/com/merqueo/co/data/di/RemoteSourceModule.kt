package com.merqueo.co.data.di

import com.merqueo.co.data.remoteSource.IMoviesRemoteSource
import com.merqueo.co.data.remoteSource.MoviesRemoteSource
import org.koin.dsl.module

val moduleRemoteSource = module {
    single<IMoviesRemoteSource> { MoviesRemoteSource(get()) }
}