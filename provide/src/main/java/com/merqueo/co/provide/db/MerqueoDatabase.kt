package com.merqueo.co.provide.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.merqueo.co.provide.db.converters.FromListOfIntToStringConverter
import com.merqueo.co.provide.db.converters.FromListOfStringsToStringConverter
import com.merqueo.co.provide.db.dao.IMoviesDao
import com.merqueo.co.models.entities.MovieEntity
@Database(
    entities = [
        MovieEntity::class
    ], version = 3
)
@TypeConverters(FromListOfIntToStringConverter::class, FromListOfStringsToStringConverter::class)
abstract class MerqueoDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): IMoviesDao
}
