package com.merqueo.co.merqueoprueba.domain.di

import com.merqueo.co.merqueoprueba.presentation.viewModel.MainViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@InternalCoroutinesApi
val viewModelMainModule = module {
    viewModel { MainViewModel(get()) }
}