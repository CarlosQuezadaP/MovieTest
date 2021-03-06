package com.merqueo.co.data.di

import com.merqueo.co.infraestructura.deviceHardware.ConnectivityImp
import com.merqueo.co.usecases.Connectivity
import org.koin.dsl.module

val connectivityModule = module {
    single<Connectivity> { ConnectivityImp(get()) }
}