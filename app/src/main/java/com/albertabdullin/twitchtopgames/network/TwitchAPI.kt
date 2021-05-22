package com.albertabdullin.twitchtopgames.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap


private const val BASE_URL = "https://api.twitch.tv/kraken/"

interface TwitchTopGamesAPI {
    @GET("games/top/")
    suspend fun getNextTopGames(
        @Header("Accept") accept: String = "application/vnd.twitchtv.v5+json",
        @Header("Client-ID") clientId: String = "sd4grh0omdj9a31exnpikhrmsu3v46",
        @QueryMap filters: Map<String, String>): Root
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

object TwitchAPI {
    val topGames: TwitchTopGamesAPI by lazy {
        retrofit.create(TwitchTopGamesAPI::class.java)
    }
}