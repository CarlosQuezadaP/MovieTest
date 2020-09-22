package com.merqueo.co.provide.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.merqueo.co.provide.db.converters.FromListOfIntToStringConverter
import com.merqueo.co.provide.db.converters.FromListOfStringsToStringConverter
import com.merqueo.co.provide.db.dao.IGenresDao
import com.merqueo.co.provide.db.dao.IMoviesDao
import com.merqueo.co.models.entities.GenreEntity
import com.merqueo.co.models.entities.MovieEntity
@Database(
    entities = [
        GenreEntity::class,
        MovieEntity::class
    ], version = 1
)
@TypeConverters(FromListOfIntToStringConverter::class, FromListOfStringsToStringConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getGenresDao(): IGenresDao
    abstract fun getMoviesDao(): IMoviesDao
}
