package com.merqueo.co.merqueoprueba.application

import android.app.Application
import com.co.merqueo.shoppingcart.dominio.di.fragmentShoppingModule
import com.co.merqueo.shoppingcart.dominio.di.moduleServiceShopping
import com.co.merqueo.shoppingcart.dominio.di.moduleViewServiceShopping
import com.merqueo.co.data.source.di.connectivityModule
import com.merqueo.co.data.source.di.entityToDomainConverterModule
import com.merqueo.co.data.source.di.moduleLocalSource
import com.merqueo.co.data.source.di.moduleRemoteSource
import com.merqueo.co.merqueoprueba.domain.di.*
import com.merqueo.co.provide.di.databaseModule
import com.merqueo.co.provide.di.networkModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin


@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@FlowPreview
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
                    homeFragmentModule,
                    moduleRemoteSource,
                    moduleLocalSource,
                    serviceMovieModule,
                    serviceDetailMovieModule,
                    viewModelMovie,
                    connectivityModule,
                    mainModule,
                    viewModelMainModule,
                    viewModelDetailMovie,
                    moduleServiceShopping,
                    fragmentShoppingModule,
                    moduleViewServiceShopping,
                    entityToDomainConverterModule
                )
            )
        }


    }
}