package com.merqueo.co.data.di

import com.merqueo.co.data.localSource.IMoviesLocalSource
import com.merqueo.co.data.localSource.MoviesLocalSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val moduleLocalSource = module {
    single<IMoviesLocalSource> { MoviesLocalSource(get(), get()) }
}