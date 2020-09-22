package com.merqueo.co.provide.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.merqueo.co.provide.db.dao.base.IBaseDao
import com.merqueo.co.models.entities.GenreEntity

@Dao
interface IGenresDao : IBaseDao<GenreEntity> {

    @Query("SELECT * FROM GenreEntity ORDER BY name ASC")
    suspend fun getAll(): List<GenreEntity>
}