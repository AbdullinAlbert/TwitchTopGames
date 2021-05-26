package com.albertabdullin.twitchtopgames.repository

import android.content.Context
import android.net.ConnectivityManager
import androidx.paging.ExperimentalPagingApi
import com.albertabdullin.twitchtopgames.database.TwitchTopGamesDatabase
import com.albertabdullin.twitchtopgames.network.TwitchAPI
import com.albertabdullin.twitchtopgames.util.paging_library.TopGamesRemoteMediator

class TwitchTopGamesRepository(private val context: Context) {
    private val twitchAPI = TwitchAPI.topGames
    private val database = TwitchTopGamesDatabase.getInstance(context).gameDAO

    @ExperimentalPagingApi
    fun getRemoteMediator(): TopGamesRemoteMediator {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
        return TopGamesRemoteMediator(cm.isDefaultNetworkActive, database, twitchAPI)
    }
    fun getDataFromDatabase() = database.getAllData()
}
