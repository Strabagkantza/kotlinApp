package com.lina.kotlinapp.ws

class PokemonService {

    private val repository = CallApi.pokemonRepository

    suspend fun getPokemonList(id: Int) = repository.getAllPokemons(id)

    suspend fun getPokemonId(id: String) = repository.getPokemon(id)

}