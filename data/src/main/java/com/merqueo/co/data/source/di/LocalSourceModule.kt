package com.merqueo.co.data.source.di

import com.merqueo.co.data.source.local.IMoviesLocalSource
import com.merqueo.co.data.source.local.MoviesLocalSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val moduleLocalSource = module {
    single<IMoviesLocalSource> { MoviesLocalSource(get(), get()) }
}