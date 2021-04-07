package com.merqueo.co.merqueoprueba.di

import com.merqueo.co.merqueoprueba.presentation.viewModel.DetailViewModel
import com.merqueo.co.merqueoprueba.presentation.viewModel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelMovie = module {
    viewModel {
        MovieViewModel(get(), get())
    }
}

val viewModelDetailMovie = module {
    viewModel {
        DetailViewModel(get(), get())
    }
}