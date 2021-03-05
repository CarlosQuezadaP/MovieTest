package com.merqueo.co.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.merqueo.co.data.source.db.converters.FromListOfIntToStringConverter
import com.merqueo.co.data.source.db.converters.FromListOfStringsToStringConverter
import com.merqueo.co.data.source.db.dao.IMoviesDao
import com.merqueo.co.data.source.entities.MovieEntity
@Database(
    entities = [
        MovieEntity::class
    ], version = 3
)
@TypeConverters(FromListOfIntToStringConverter::class, FromListOfStringsToStringConverter::class)
abstract class MerqueoDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): IMoviesDao
}
