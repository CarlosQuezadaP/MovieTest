package com.merqueo.co.merqueoprueba.domain.di

import com.merqueo.co.merqueoprueba.domain.servicio.IServiceMovie
import com.merqueo.co.merqueoprueba.domain.servicio.IserviceDetail
import com.merqueo.co.merqueoprueba.domain.servicio.ServiceDetail
import com.merqueo.co.merqueoprueba.domain.servicio.ServiceMovie
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@FlowPreview
val serviceMovieModule = module {
    single<IServiceMovie> { ServiceMovie(get(), get(), get()) }
}

val serviceDetailMovieModule = module {
    single<IserviceDetail> { ServiceDetail(get()) }
}