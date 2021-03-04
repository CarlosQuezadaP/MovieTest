package com.merqueo.co.data.source.di

import com.merqueo.co.infraestructura.deviceHardware.ConnectivityImp
import com.merqueo.co.usecases.Connectivity
import org.koin.dsl.module

val connectivityModule = module {
    single<Connectivity> { ConnectivityImp(get()) }
}