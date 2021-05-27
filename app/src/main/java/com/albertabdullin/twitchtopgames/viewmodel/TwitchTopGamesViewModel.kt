package com.albertabdullin.twitchtopgames.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig

import com.albertabdullin.twitchtopgames.repository.TwitchTopGamesRepository


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