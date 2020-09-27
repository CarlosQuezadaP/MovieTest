package com.merqueo.co.infraestructura.source.di

import com.merqueo.co.infraestructura.source.local.IMoviesLocalSource
import com.merqueo.co.infraestructura.source.local.MoviesLocalSource
import org.koin.dsl.module

val moduleLocalSource = module {
    single<IMoviesLocalSource> { MoviesLocalSource(get()) }
}