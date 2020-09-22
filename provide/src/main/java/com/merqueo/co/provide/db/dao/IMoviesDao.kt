package com.merqueo.co.provide.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.merqueo.co.provide.db.dao.base.IBaseDao
import com.merqueo.co.models.entities.MovieEntity

@Dao
interface IMoviesDao : IBaseDao<MovieEntity> {

    @Query("SELECT * FROM MovieEntity WHERE genreIds LIKE '%' || :genreId  || '%' ORDER BY releaseDate DESC")
    fun getAllByGenreId(genreId: Int): androidx.paging.DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM MovieEntity WHERE isPopular = :isPopular ORDER BY popularity DESC")
    fun getAllByPopular(isPopular: Boolean = true): androidx.paging.DataSource.Factory<Int, MovieEntity>

    @Query("SELECT COUNT(*) FROM MovieEntity WHERE genreIds LIKE '%' || :genreId  || '%' ORDER BY releaseDate DESC")
    suspend fun getCount(genreId: Int): Int

    @Query("SELECT * FROM MovieEntity WHERE id = :movieId LIMIT 1")
    suspend fun getById(movieId: Int): MovieEntity?

    @Query("SELECT COUNT(*) FROM MovieEntity WHERE isPopular = :isPopular")
    suspend fun getPopularCount(isPopular: Boolean = true): Int
}