package com.merqueo.co.merqueoprueba.domain.di

import com.merqueo.co.merqueoprueba.presentation.viewModel.DetailViewModel
import com.merqueo.co.merqueoprueba.presentation.viewModel.MovieViewModel
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

@ExperimentalCoroutinesApi
val viewModelDetailMovie = module {
    viewModel {
        DetailViewModel(get(),get())
    }
}