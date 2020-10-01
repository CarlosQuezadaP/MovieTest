package com.merqueo.co.infraestructura.source.di

import com.merqueo.co.infraestructura.source.remote.IMoviesRemoteSource
import com.merqueo.co.infraestructura.source.remote.MoviesRemoteSource
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.dsl.module

@InternalCoroutinesApi
val moduleRemoteSource = module {
    single<IMoviesRemoteSource> { MoviesRemoteSource(get()) }
}