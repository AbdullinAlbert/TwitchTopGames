package com.albertabdullin.twitchtopgames.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class TwitchTopGamesDatabase: RoomDatabase() {
    abstract val gameDAO: GameDAO

    companion object {
        @Volatile
        private var INSTANCE: TwitchTopGamesDatabase? = null
        fun getInstance(context: Context): TwitchTopGamesDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context,
                            TwitchTopGamesDatabase::class.java,
                            "twitch_top_games_database")
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}