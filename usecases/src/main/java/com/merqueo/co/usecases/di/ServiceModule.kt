package com.merqueo.co.merqueoprueba.domain.di

import com.merqueo.co.usecases.usecases.IServiceMovie
import com.merqueo.co.usecases.usecases.IserviceDetail
import com.merqueo.co.usecases.usecases.ServiceDetail
import com.merqueo.co.usecases.usecases.ServiceMovie
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@FlowPreview
val serviceMovieModule = module {
    single<IServiceMovie> {
        ServiceMovie(
            get(),
            get(),
            get()
        )
    }
}

val serviceDetailMovieModule = module {
    single<IserviceDetail> { ServiceDetail(get()) }
}