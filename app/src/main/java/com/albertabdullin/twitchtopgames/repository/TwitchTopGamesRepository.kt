package com.albertabdullin.twitchtopgames.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albertabdullin.twitchtopgames.database.GameEntity
import com.albertabdullin.twitchtopgames.database.TwitchTopGamesDatabase
import com.albertabdullin.twitchtopgames.network.TwitchAPI
import com.albertabdullin.twitchtopgames.network.convertToDatabaseListOfEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TwitchTopGamesRepository(private val context: Context) {

    private val twitchAPI = TwitchAPI.topGames
    private val database = TwitchTopGamesDatabase.getInstance(context).gameDAO

    private val _games = MutableLiveData<List<GameEntity>>()

    val games : LiveData<List<GameEntity>>
        get() = _games

    suspend fun getNextItemsFromTop() {
        _games.value = database.getAllData()
        withContext(Dispatchers.IO) {
            val root = twitchAPI.getNextTopGames(filters = mapOf<String, String>())
            database.deleteAllData()
            database.insertData(root.convertToDatabaseListOfEntity())
            _games.postValue(database.getAllData())
        }
    }


}