package com.lina.kotlinapp

import android.app.Application
import com.lina.kotlinapp.viewmodel.ListPokemonViewModel
import com.lina.kotlinapp.viewmodel.PokemonViewModel
import com.lina.kotlinapp.ws.PokemonService
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainActivityKoin : Application() {


    var listofModules = module {
       single { PokemonService() }
       single { ListPokemonViewModel() }
       single { PokemonViewModel() }
    }


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainActivityKoin)
            koin.loadModules(listOf(listofModules))
            koin.createRootScope()
        }
    }
}