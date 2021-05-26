package com.albertabdullin.twitchtopgames.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GameDAO {
    @Query("SELECT * FROM game ORDER BY viewersCount DESC")
    fun getAllData(): PagingSource<Int, GameEntity>

    @Query("DELETE FROM game")
    suspend fun deleteAllData()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(list: List<GameEntity>)
}