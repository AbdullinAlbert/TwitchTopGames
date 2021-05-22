package com.albertabdullin.twitchtopgames.network

import com.albertabdullin.twitchtopgames.database.GameEntity
import com.squareup.moshi.Json

data class Root(
    @Json(name = "_total") val total: Int,
    val top: List<Info>
)

data class Info(
    val channels: Int,
    val viewers: Int,
    val game: Game
)

data class Game(
    @Json(name = "_id") val id: Int,
    val box: Box,
    @Json(name = "giantbomb_id") val giantBombId: Int,
    val logo: Logo,
    val name: String
)

data class Box(
    val large: String,
    val medium: String,
    val small: String,
    val template: String
)

data class Logo(
    val large: String,
    val medium: String,
    val small: String,
    val template: String
)

fun Root.convertToDatabaseListOfEntity(): List<GameEntity> = top.map {
        GameEntity(it.game.id, it.game.box.large, it.game.name, it.viewers, it.channels)
    }