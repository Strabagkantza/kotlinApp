package com.lina.kotlinapp.model

import com.squareup.moshi.Json

data class ListPokemon(
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "url") val url: String?
)