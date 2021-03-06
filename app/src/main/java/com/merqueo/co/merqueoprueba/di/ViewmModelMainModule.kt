package com.merqueo.co.merqueoprueba.di

import com.merqueo.co.merqueoprueba.presentation.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelMainModule = module {
    viewModel { MainViewModel(get()) }
}