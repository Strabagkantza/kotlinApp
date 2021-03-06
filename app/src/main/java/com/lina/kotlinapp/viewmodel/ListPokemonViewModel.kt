package com.lina.kotlinapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.lina.kotlinapp.ws.PokemonService
import com.lina.kotlinapp.ws.handler.ExceptionHandler
import kotlinx.coroutines.*

class ListPokemonViewModel : ViewModel() {

    private val service = PokemonService()

    private val job = Job()

    fun pokemons(id: Int) = liveData(job + Dispatchers.Main + ExceptionHandler.defaultHandler) {

        delay(2000)
        val response = service.getPokemonList(id)
        emit(response)

    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
