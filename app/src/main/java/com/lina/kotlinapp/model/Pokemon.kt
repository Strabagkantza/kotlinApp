package com.lina.kotlinapp.model

import com.squareup.moshi.Json

data class Pokemon(
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "weight") val weight: String?,
    @field:Json(name = "height") val height: String?,
    @field:Json(name = "abilities") val abilities: MutableList<Abilities>,
    @field:Json(name = "moves") val moves: MutableList<Moves>,
    @field:Json(name = "sprites") val sprites: Sprites
)