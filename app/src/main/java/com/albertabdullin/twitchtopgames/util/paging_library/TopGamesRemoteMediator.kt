package com.albertabdullin.twitchtopgames.util.paging_library

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.albertabdullin.twitchtopgames.database.GameDAO
import com.albertabdullin.twitchtopgames.database.GameEntity
import com.albertabdullin.twitchtopgames.network.TwitchTopGamesAPI
import com.albertabdullin.twitchtopgames.network.convertToDatabaseListOfEntity
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class TopGamesRemoteMediator(
        private val isThereNetworkConnection: Boolean,
        private val database: GameDAO,
        private val twitchAPI: TwitchTopGamesAPI
) : RemoteMediator<Int, GameEntity>() {

    private var offset = 0

    override suspend fun load(loadType: LoadType,
                              state: PagingState<Int, GameEntity>): MediatorResult {
        return try {
            val currentOffset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> offset
            }
            val map = mapOf("offset" to currentOffset.toString())
            val response = twitchAPI.getNextTopGames(query = map)
            if (loadType == LoadType.REFRESH) {
                database.deleteAllData()
            }
            database.insertData(response.convertToDatabaseListOfEntity())
            offset += response.top.size
           val isEndOfPagination = offset == response.total
            MediatorResult.Success(endOfPaginationReached = isEndOfPagination)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return if (isThereNetworkConnection) InitializeAction.LAUNCH_INITIAL_REFRESH
        else InitializeAction.SKIP_INITIAL_REFRESH
    }

}