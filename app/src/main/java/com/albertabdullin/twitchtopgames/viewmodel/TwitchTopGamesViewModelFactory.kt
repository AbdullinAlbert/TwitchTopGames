package com.albertabdullin.twitchtopgames.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class TwitchTopGamesViewModelFactory(private val context: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TwitchTopGamesViewModel::class.java))
            return TwitchTopGamesViewModel(context) as T
        else throw IllegalArgumentException()
    }
}