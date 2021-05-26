package com.albertabdullin.twitchtopgames.util.paging_library

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.albertabdullin.twitchtopgames.database.GameEntity
import com.albertabdullin.twitchtopgames.network.TwitchTopGamesAPI
import com.albertabdullin.twitchtopgames.network.convertToDatabaseListOfEntity

class TopGamesSource(private val twitchAPI: TwitchTopGamesAPI) : PagingSource<Int, GameEntity>() {
    private val offsetPeriod = 10
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameEntity> {
        try {
            val nextPageNumber = params.key ?: 0
            val pKey = if (nextPageNumber > 0) nextPageNumber - 1 else null
            val nKey = nextPageNumber + 1
            val offset = nextPageNumber * offsetPeriod
            val queryParams = mapOf("offset" to offset.toString())
            val response = twitchAPI.getNextTopGames(query = queryParams)
            return LoadResult.Page(
                data = response.convertToDatabaseListOfEntity(),
                prevKey = pKey,
                nextKey = nKey
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GameEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}