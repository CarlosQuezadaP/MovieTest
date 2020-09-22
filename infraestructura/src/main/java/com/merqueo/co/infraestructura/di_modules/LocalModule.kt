package com.merqueo.co.infraestructura.di_modules

import com.merqueo.co.infraestructura.source.local.GenresLocalSource
import com.merqueo.co.infraestructura.source.local.IGenresLocalSource
import com.merqueo.co.infraestructura.source.local.IMoviesLocalSource
import com.merqueo.co.infraestructura.source.local.MoviesLocalSource
import org.koin.dsl.module

val repositoryLocalModule = module {

    single<IGenresLocalSource> {
        GenresLocalSource(get())
    }

    single<IMoviesLocalSource> {
        MoviesLocalSource(get())
    }


}