package com.albertabdullin.twitchtopgames.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.albertabdullin.twitchtopgames.repository.TwitchTopGamesRepository
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class TwitchTopGamesViewModel(context: Application): AndroidViewModel(context) {
    private val repository = TwitchTopGamesRepository(context)
    private val _networkStateError = MutableLiveData<Boolean>()
    val networkStateError: LiveData<Boolean>
        get() = _networkStateError

    fun getGamesList() = repository.games

    init {
        getFirstTenOfTopGames()
    }

    fun getFirstTenOfTopGames() =  viewModelScope.launch {
        try {
            repository.getNextItemsFromTop()
        } catch (e: UnknownHostException) {
            _networkStateError.value = true
            _networkStateError.value = false
        }
    }
}