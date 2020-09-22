package com.merqueo.co.merqueoprueba.application

import android.app.Application
import com.merqueo.co.infraestructura.di_modules.repositoryLocalModule
import com.merqueo.co.infraestructura.di_modules.repositoryRemoteModule
import com.merqueo.co.provide.di_modules.databaseModule
import com.merqueo.co.provide.di_modules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MerqueoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MerqueoApp)
            modules(
                listOf(
                    databaseModule, networkModule, repositoryLocalModule,
                    repositoryRemoteModule
                )
            )
        }
    }

}