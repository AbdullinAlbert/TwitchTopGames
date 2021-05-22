package com.albertabdullin.twitchtopgames.database

data class GameEntity(
    val id: Int,
    val cover: String,
    val name: String,
    val viewersCount: Int,
    val channelCount: Int
)
