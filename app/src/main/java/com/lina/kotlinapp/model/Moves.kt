package com.lina.kotlinapp.model

import com.squareup.moshi.Json

data class Moves(
    @field:Json(name = "move") val move: Details?
)