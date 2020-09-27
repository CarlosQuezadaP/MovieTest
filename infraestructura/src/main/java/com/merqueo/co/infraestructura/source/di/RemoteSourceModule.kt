package com.merqueo.co.infraestructura.source.di

import com.merqueo.co.infraestructura.source.remote.IMoviesRemoteSource
import com.merqueo.co.infraestructura.source.remote.MoviesRemoteSource
import org.koin.dsl.module

val moduleRemoteSource = module {
    single<IMoviesRemoteSource> { MoviesRemoteSource(get()) }
}