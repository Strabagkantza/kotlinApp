package com.lina.kotlinapp.model

import com.squareup.moshi.Json

data class Sprites(
    @field:Json(name = "front_default") val front_default: String?
)