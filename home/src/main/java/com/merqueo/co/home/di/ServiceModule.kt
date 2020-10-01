package com.merqueo.co.home.di

import com.merqueo.co.home.domain.IServiceMovie
import com.merqueo.co.home.domain.IserviceDetail
import com.merqueo.co.home.domain.ServiceDetail
import com.merqueo.co.home.domain.ServiceMovie
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@FlowPreview
val serviceMovieModule = module {
    single<IServiceMovie> { ServiceMovie(get(), get(), get()) }
}

val serviceDetailMovieModule = module {
    single<IserviceDetail> { ServiceDetail(get()) }
}