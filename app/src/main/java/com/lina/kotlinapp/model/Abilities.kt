package com.lina.kotlinapp.model

import com.squareup.moshi.Json

data class Abilities(
    @field:Json(name = "ability") val ability: Details?,
    @field:Json(name = "is_hidden") val is_hidden: Boolean?
)