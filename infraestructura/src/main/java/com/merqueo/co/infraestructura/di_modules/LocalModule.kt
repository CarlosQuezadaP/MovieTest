package com.merqueo.co.infraestructura.di_modules

import com.merqueo.co.infraestructura.repository.local.GenresLocalRepository
import com.merqueo.co.infraestructura.repository.local.IGenresLocalRepository
import com.merqueo.co.infraestructura.repository.local.IMoviesLocalRepository
import com.merqueo.co.infraestructura.repository.local.MoviesLocalRepository
import org.koin.dsl.module

val repositoryLocalModule = module {

    single<IGenresLocalRepository> {
        GenresLocalRepository(get())
    }

    single<IMoviesLocalRepository> {
        MoviesLocalRepository(get())
    }


}