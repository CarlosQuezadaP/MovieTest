package com.merqueo.co.infraestructura.source.di

import com.merqueo.co.core.Connectivity
import com.merqueo.co.infraestructura.deviceHardware.ConnectivityImp
import org.koin.dsl.module

val connectivityModule = module {
    single<Connectivity> { ConnectivityImp(get()) }
}