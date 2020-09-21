package com.merqueo.co.provide.di_modules

import androidx.room.Room
import com.merqueo.co.provide.db.AppDatabase
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, "MerqueoDB.db"
        ).build()
    }

    single { single { get<AppDatabase>().getGenresDao() } }
    single { single { get<AppDatabase>().getMoviesDao() } }

}