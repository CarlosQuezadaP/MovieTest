package com.merqueo.co.data.di

import com.merqueo.co.data.repository.IMovieRepo
import com.merqueo.co.data.repository.MoviesRepo
import org.koin.dsl.module

val movieRepositoryModule = module {
    single<IMovieRepo> { MoviesRepo(get(), get(), get()) }
}