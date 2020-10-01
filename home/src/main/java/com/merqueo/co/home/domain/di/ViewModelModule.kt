package com.merqueo.co.home.domain.di

import com.merqueo.co.home.presentacion.viewModel.DetailViewModel
import com.merqueo.co.home.presentacion.viewModel.MovieViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
val viewModelMovie = module {
    viewModel {
        MovieViewModel(get())
    }
}

val viewModelDetailMovie = module {
    viewModel {
        DetailViewModel(get())
    }
}