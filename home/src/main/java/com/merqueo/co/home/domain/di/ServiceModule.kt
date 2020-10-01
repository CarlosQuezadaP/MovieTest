package com.merqueo.co.home.domain.di

import com.merqueo.co.home.domain.service.IServiceMovie
import com.merqueo.co.home.domain.service.IserviceDetail
import com.merqueo.co.home.domain.service.ServiceDetail
import com.merqueo.co.home.domain.service.ServiceMovie
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@FlowPreview
val serviceMovieModule = module {
    single<IServiceMovie> { ServiceMovie(get(), get(), get()) }
}

val serviceDetailMovieModule = module {
    single<IserviceDetail> { ServiceDetail(get()) }
}