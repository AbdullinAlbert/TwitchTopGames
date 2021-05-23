package com.albertabdullin.twitchtopgames.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDAO {
    @Query("SELECT * FROM game")
    suspend fun getAllData(): List<GameEntity>

    @Query("DELETE FROM game")
    suspend fun deleteAllData()

    @Insert
    suspend fun insertData(list: List<GameEntity>)
}