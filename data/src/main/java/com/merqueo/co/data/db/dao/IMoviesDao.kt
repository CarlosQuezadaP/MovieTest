package com.merqueo.co.data.db.dao

import androidx.room.*
import com.merqueo.co.data.db.entities.MovieEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@Dao
interface IMoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(list: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: MovieEntity)

    @Query("UPDATE MovieEntity SET onStore=:onStore WHERE id = :id")
    fun update(onStore: Boolean, id: Int): Int

    @Query("UPDATE MovieEntity SET onStore=:onStore WHERE id = :id")
    fun updateToFailStore(onStore: Boolean = false, id: Int): Int

    @Update
    fun updateAll(movies: List<MovieEntity>): Int

    @Update
    fun updateMovie(movies: MovieEntity): Int


    @Query("SELECT COUNT(*) FROM MovieEntity WHERE onStore = :onStore")
    fun getOnStoreCount(onStore: Boolean = true): Flow<Int>


    @Delete
    fun delete(data: MovieEntity)


    @Query("SELECT * FROM MovieEntity WHERE id = :movieId LIMIT 1")
    fun getById(movieId: Int): MovieEntity

    @Query("SELECT COUNT(*) FROM MovieEntity WHERE isPopular = :isPopular")
    fun getPopularCount(isPopular: Boolean = true): Int

    @Query("SELECT * FROM MovieEntity  WHERE onStore = :onStore")
    fun getAllByStore(onStore: Boolean = true): List<MovieEntity>

    @Query("SELECT * FROM MovieEntity  WHERE onStore = :onStore")
    fun getAllByStore2(onStore: Boolean = true): Flow<List<MovieEntity>>


    @Query("SELECT * FROM MovieEntity")
    fun getAllList(): List<MovieEntity>

    @Query("SELECT * FROM MovieEntity")
    fun getAll(): Flow<List<MovieEntity>>

    @ExperimentalCoroutinesApi
    fun getMovieDistinctUntilChanged() =
        getAll()


}