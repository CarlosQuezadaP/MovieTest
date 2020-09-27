package com.merqueo.co.merqueoprueba.application

import android.app.Application
import com.merqueo.co.home.di.homeFragmentModule
import com.merqueo.co.home.di.serviceMovieModule
import com.merqueo.co.home.di.viewModelMovie
import com.merqueo.co.infraestructura.source.di.moduleLocalSource
import com.merqueo.co.infraestructura.source.di.moduleRemoteSource
import com.merqueo.co.provide.di.databaseModule
import com.merqueo.co.provide.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class
MerqueoApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MerqueoApp)
            fragmentFactory()
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    homeFragmentModule,
                    moduleRemoteSource,
                    moduleLocalSource,
                    serviceMovieModule,
                    viewModelMovie
                )
            )
        }


    }
}