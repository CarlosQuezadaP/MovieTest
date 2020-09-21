package com.merqueo.co.provide.di_modules

import com.merqueo.co.infraestructura.network.api.IMovieApi
import com.merqueo.co.provide.network.createOkHttpClient
import com.merqueo.co.provide.network.createWebServiceApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit


val networkModule = module {

    single { createOkHttpClient() }
    single<IMovieApi>{ createWebServiceApi(get())}
}
