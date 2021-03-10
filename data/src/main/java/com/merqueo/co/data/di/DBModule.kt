package com.merqueo.co.provide.di

import androidx.room.Room
import com.merqueo.co.data.db.MerqueoDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(androidApplication(), MerqueoDatabase::class.java, "db")
            .allowMainThreadQueries()
            .build()
    }

    factory { get<MerqueoDatabase>().getMoviesDao() }

}

