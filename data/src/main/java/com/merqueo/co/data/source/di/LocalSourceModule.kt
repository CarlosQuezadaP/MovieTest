package com.merqueo.co.data.source.di

import com.merqueo.co.data.source.local.IMoviesLocalSource
import com.merqueo.co.data.source.local.MoviesLocalSource
import org.koin.dsl.module

val moduleLocalSource = module {
    single<IMoviesLocalSource> { MoviesLocalSource(get()) }
}