package com.merqueo.co.infraestructura.di_modules

import com.merqueo.co.infraestructura.source.remote.GenresRemoteSource
import com.merqueo.co.infraestructura.source.remote.IGenresRemoteSource
import com.merqueo.co.infraestructura.source.remote.IMoviesRemoteSource
import com.merqueo.co.infraestructura.source.remote.MoviesRemoteSource
import org.koin.dsl.module


val repositoryRemoteModule = module {

    single<IGenresRemoteSource> {
        GenresRemoteSource(get())
    }

    single<IMoviesRemoteSource> {
        MoviesRemoteSource(get())
    }


}