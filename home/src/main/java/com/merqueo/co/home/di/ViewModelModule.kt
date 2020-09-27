package com.merqueo.co.home.di

import com.merqueo.co.home.viewModel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelMovie = module {
    viewModel {
        MovieViewModel(get())
    }
}