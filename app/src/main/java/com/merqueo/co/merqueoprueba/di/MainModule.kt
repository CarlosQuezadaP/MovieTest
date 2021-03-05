package com.merqueo.co.merqueoprueba.domain.di

import com.merqueo.co.usecases.usecases.IServiceMain
import com.merqueo.co.usecases.usecases.ServiceMain
import org.koin.dsl.module

val mainModule = module {
    single<IServiceMain> { ServiceMain(get()) }
}

