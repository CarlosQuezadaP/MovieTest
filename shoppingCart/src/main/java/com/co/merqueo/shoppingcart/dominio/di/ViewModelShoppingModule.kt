package com.co.merqueo.shoppingcart.dominio.di

import com.co.merqueo.shoppingcart.presentation.viewModel.ViewModelShopping
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleViewServiceShopping = module {
    viewModel { ViewModelShopping(get()) }
}