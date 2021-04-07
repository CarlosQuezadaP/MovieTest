package com.merqueo.co.merqueoprueba.domain.di

import com.merqueo.co.usecases.usecases.*
import org.koin.dsl.module

val usecaseGetMovieModule = module {
    single<IGetMoviesUseCase> {
        GetMoviesUseCase(
            get(),
            get()
        )
    }
}

val useCaseDetailMovieModule = module {
    single<IMovieDetailUseCase> { MovieDetailUseCase(get()) }
}

val useCaseUpdateMovieModule = module {
    single<IUpdateMovieUseCase> { UpdateMovieUseCase(get()) }
}

val useCaseGetMovieShopShopping = module {
    single<IGetMoviesShopCarUseCase> {
        GetMoviesShopCarUseCase(
            get()
        )
    }
}

val useCasedeleteMoviesFromShop = module(override = true) {
    single<IDeleteMoviesFromShopUseCase> {
        DeleteMoviesFromShopUseCase(
            get()
        )
    }
}


