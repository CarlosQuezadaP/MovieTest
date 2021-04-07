package com.merqueo.co.data.di

import com.merqueo.co.data.localSource.IMoviesLocalSource
import com.merqueo.co.data.localSource.MoviesLocalSource
import org.koin.dsl.module

val moduleLocalSource = module {
    single<IMoviesLocalSource> { MoviesLocalSource(get(), get()) }
}