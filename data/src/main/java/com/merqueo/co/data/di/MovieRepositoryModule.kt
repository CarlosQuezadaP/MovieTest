package com.merqueo.co.data.di

import com.merqueo.co.data.repository.IMovieRepo
import com.merqueo.co.data.repository.MoviesRepo
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@FlowPreview
val movieRepositoryModule = module {
    single<IMovieRepo> { MoviesRepo(get(), get(), get()) }
}