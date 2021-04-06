package com.merqueo.co.merqueoprueba.di

import com.merqueo.co.usecases.usecases.DeleteMoviesFromShopUseCase
import com.merqueo.co.usecases.usecases.IDeleteMoviesFromShopUseCase
import org.koin.dsl.module

val mainModule = module {
    single<IDeleteMoviesFromShopUseCase> { DeleteMoviesFromShopUseCase(get()) }
}

