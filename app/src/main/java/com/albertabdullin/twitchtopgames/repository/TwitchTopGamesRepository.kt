package com.albertabdullin.twitchtopgames.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albertabdullin.twitchtopgames.database.GameEntity
import com.albertabdullin.twitchtopgames.network.TwitchAPI
import com.albertabdullin.twitchtopgames.network.convertToDatabaseListOfEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TwitchTopGamesRepository {

    private val twitchAPI = TwitchAPI.topGames

    private val _games = MutableLiveData<List<GameEntity>>()

    val games : LiveData<List<GameEntity>>
        get() = _games

    suspend fun getNextItemsFromTop() {
        withContext(Dispatchers.IO) {
            val root = twitchAPI.getNextTopGames(filters = mapOf<String, String>())
            _games.postValue(root.convertToDatabaseListOfEntity())
        }
    }
}