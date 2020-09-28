package com.lina.kotlinapp.model

import com.squareup.moshi.Json

data class Details(
    @field:Json(name = "name") val name: String?
)