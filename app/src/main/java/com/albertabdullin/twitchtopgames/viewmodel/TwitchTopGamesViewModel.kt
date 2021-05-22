package com.albertabdullin.twitchtopgames.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.albertabdullin.twitchtopgames.repository.TwitchTopGamesRepository
import kotlinx.coroutines.launch

class TwitchTopGamesViewModel(private val context: Application): AndroidViewModel(context) {
    private val repository = TwitchTopGamesRepository()

    fun getGamesList() = repository.games

    init {
        viewModelScope.launch {
            repository.getNextItemsFromTop()
        }
    }

}