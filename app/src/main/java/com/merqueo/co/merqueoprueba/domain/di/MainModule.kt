package com.merqueo.co.merqueoprueba.domain.di

import com.merqueo.co.usecases.IServiceMain
import com.merqueo.co.usecases.ServiceMain
import org.koin.dsl.module

val mainModule = module {
    single<com.merqueo.co.usecases.IServiceMain> { com.merqueo.co.usecases.ServiceMain(get()) }
}

