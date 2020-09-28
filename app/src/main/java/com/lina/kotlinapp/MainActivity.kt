package com.lina.kotlinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.lina.kotlinapp.adapter.PokemonAdapter
import com.lina.kotlinapp.model.ListPokemon
import com.lina.kotlinapp.viewmodel.ListPokemonViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val listPokemonViewModel: ListPokemonViewModel by viewModel()

    private var page: Int = 0
    private var counter: Int = 0

    private val pokemonAdapter by lazy {
        PokemonAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listPokemon.adapter = pokemonAdapter
        pokemonAdapter.onClickItem = this::openPokemonDetail
        getListPokemons(page)

        listPokemon.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if( !recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                    Log.e("Scroll end reached","----")
                    if (page < counter) {
                        page = page + 20
                        getListPokemons(page)
                    }

                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        page = 0
        counter = 0
        getListPokemons(page)
    }

    private fun openPokemonDetail(item : ListPokemon){
        val intent = Intent(this, PokemonActivity::class.java)
        var id = item.url?.substringAfter("pokemon/")
        id = id?.substringBefore("/")
        intent.putExtra("id",id)
        startActivity(intent)
    }

    private fun getListPokemons(id: Int){
        progress.visibility = View.VISIBLE
        listPokemonViewModel.pokemons(id).observe(this, Observer {
            if (id == 0) {
                pokemonAdapter.updateList(it.results)
            } else {
                pokemonAdapter.addList(it.results)
                listPokemon.scrollToPosition(id)
            }
            progress.visibility = View.GONE
            counter = it.count!!
        })
    }
}

