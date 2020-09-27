package com.merqueo.co.provide.di

import androidx.room.Room
import com.merqueo.co.provide.db.AppDatabase
import org.koin.dsl.module

val databaseModule = module {

    single { Room.databaseBuilder(get(), AppDatabase::class.java, "db").build() }

    single { get<AppDatabase>().getMoviesDao() }


}

