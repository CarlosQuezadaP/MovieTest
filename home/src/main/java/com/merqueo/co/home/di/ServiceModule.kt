package com.merqueo.co.home.di

import com.merqueo.co.home.domain.IServiceMovie
import com.merqueo.co.home.domain.ServiceMovie
import org.koin.dsl.module

val serviceMovieModule = module {
    single<IServiceMovie> { ServiceMovie(get(), get(), get()) }
}