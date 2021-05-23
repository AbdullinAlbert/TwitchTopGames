package com.albertabdullin.twitchtopgames.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity(
        @PrimaryKey val id: Int,
        @ColumnInfo val cover: String,
        @ColumnInfo val name: String,
        @ColumnInfo val viewersCount: Int,
        @ColumnInfo val channelCount: Int
)
