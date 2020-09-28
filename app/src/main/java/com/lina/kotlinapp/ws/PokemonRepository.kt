package com.lina.kotlinapp.ws

import com.lina.kotlinapp.model.Pokemon
import com.lina.kotlinapp.model.ResponseListPokemon
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonRepository {

    @GET("pokemon?limit=20")
    suspend fun getAllPokemons(@Query("offset") id: Int?): ResponseListPokemon

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: String?): Pokemon


}