package com.merqueo.co.merqueoprueba.di

import com.merqueo.co.merqueoprueba.presentation.viewModel.ViewModelShopping
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleViewServiceShopping = module {
    viewModel { ViewModelShopping(get(), get()) }
}