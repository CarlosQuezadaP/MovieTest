package com.merqueo.co.provide.db.dao

import androidx.room.*
import com.merqueo.co.models.entities.MovieEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface IMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: MovieEntity)

    @Update
    fun update(data: MovieEntity): Int

    @Delete
    fun delete(data: MovieEntity)

    @Query("SELECT * FROM MovieEntity WHERE genreIds LIKE '%' || :genreId  || '%' ORDER BY releaseDate DESC")
    fun getAllByGenreId(genreId: Int): androidx.paging.DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM MovieEntity WHERE isPopular = :isPopular ORDER BY popularity DESC")
    fun getAllByPopular(isPopular: Boolean = true): androidx.paging.DataSource.Factory<Int, MovieEntity>

    @Query("SELECT COUNT(*) FROM MovieEntity WHERE genreIds LIKE '%' || :genreId  || '%' ORDER BY releaseDate DESC")
    fun getCount(genreId: Int): Int

    @Query("SELECT * FROM MovieEntity WHERE id = :movieId LIMIT 1")
    fun getById(movieId: Int): MovieEntity

    @Query("SELECT COUNT(*) FROM MovieEntity WHERE isPopular = :isPopular")
    fun getPopularCount(isPopular: Boolean = true): Int

    @Query("SELECT * FROM MovieEntity")
    fun getAll(): Flow<List<MovieEntity>>

    @ExperimentalCoroutinesApi
    fun getMovieDistinctUntilChanged() =
        getAll().distinctUntilChanged()
}