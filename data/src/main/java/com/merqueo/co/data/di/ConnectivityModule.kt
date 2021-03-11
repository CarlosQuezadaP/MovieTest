package com.merqueo.co.data.di

import com.merqueo.co.infraestructura.deviceHardware.IConnectivityImp
import com.merqueo.co.CORE.IConnectivity
import org.koin.dsl.module

val connectivityModule = module {
    single<IConnectivity> { IConnectivityImp(get()) }
}