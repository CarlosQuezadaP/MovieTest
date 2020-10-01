package com.merqueo.co.merqueoprueba.domain.di

import com.merqueo.co.merqueoprueba.domain.servicio.IServiceMain
import com.merqueo.co.merqueoprueba.domain.servicio.ServiceMain
import com.merqueo.co.merqueoprueba.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single<IServiceMain> { ServiceMain(get()) }
}

