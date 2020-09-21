package com.merqueo.co.infraestructura.di_modules

import com.merqueo.co.infraestructura.repository.remote.GenresRemoteRepository
import com.merqueo.co.infraestructura.repository.remote.IGenresRemoteRepository
import com.merqueo.co.infraestructura.repository.remote.IMoviesRemoteRepository
import com.merqueo.co.infraestructura.repository.remote.MoviesRemoteRepository
import org.koin.dsl.module


val repositoryRemoteModule = module {

    single<IGenresRemoteRepository> {
        GenresRemoteRepository(get())
    }

    single<IMoviesRemoteRepository> {
        MoviesRemoteRepository(get())
    }


}