package com.merqueo.co.merqueoprueba.application

import android.app.Application
import com.merqueo.co.data.di.*
import com.merqueo.co.merqueoprueba.di.*
import com.merqueo.co.merqueoprueba.domain.di.*
import com.merqueo.co.provide.di.databaseModule
import com.merqueo.co.provide.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class MerqueoApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MerqueoApp)
            fragmentFactory()
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    moduleRemoteSource,
                    moduleLocalSource,
                    usecaseGetMovieModule,
                    useCaseDetailMovieModule,
                    viewModelMovie,
                    connectivityModule,
                    mainModule,
                    viewModelMainModule,
                    viewModelDetailMovie,
                    useCaseGetMovieShopShopping,
                    moduleViewServiceShopping,
                    entityToDomainConverterModule,
                    useCaseUpdateMovieModule,
                    useCasedeleteMoviesFromShop,
                    movieRepositoryModule
                )
            )
        }


    }
}