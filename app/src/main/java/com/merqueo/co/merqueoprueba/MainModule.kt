package com.merqueo.co.merqueoprueba

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single<IServiceMain> { ServiceMain(get()) }
}

val viewModelMainModule = module {
    viewModel { MainViewModel(get()) }
}