package com.albertabdullin.twitchtopgames.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.core.net.ConnectivityManagerCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.albertabdullin.twitchtopgames.repository.TwitchTopGamesRepository
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class TwitchTopGamesViewModel(context: Application): AndroidViewModel(context) {
    private var repository = TwitchTopGamesRepository(context)
    @ExperimentalPagingApi
    val flow = Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = repository.getRemoteMediator()
    ) {
        repository.getDataFromDatabase()
    }.flow

}