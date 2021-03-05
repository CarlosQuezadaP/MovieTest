package com.merqueo.co.merqueoprueba.domain.di

import com.merqueo.co.usecases.IServiceMovie
import com.merqueo.co.usecases.IserviceDetail
import com.merqueo.co.usecases.ServiceDetail
import com.merqueo.co.usecases.ServiceMovie
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@FlowPreview
val serviceMovieModule = module {
    single<com.merqueo.co.usecases.IServiceMovie> {
        com.merqueo.co.usecases.ServiceMovie(
            get(),
            get(),
            get()
        )
    }
}

val serviceDetailMovieModule = module {
    single<com.merqueo.co.usecases.IserviceDetail> { com.merqueo.co.usecases.ServiceDetail(get()) }
}