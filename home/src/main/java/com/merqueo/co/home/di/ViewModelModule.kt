package com.merqueo.co.home.di

import com.merqueo.co.home.viewModel.MovieViewModel
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