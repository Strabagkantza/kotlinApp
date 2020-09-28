package com.lina.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lina.kotlinapp.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import com.lina.kotlinapp.adapter.AbilitiesAdapter
import com.lina.kotlinapp.adapter.MovesAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.progress

class PokemonActivity : AppCompatActivity() {

    private val pokemonViewModel: PokemonViewModel by viewModel()

    private val movesAdapter by lazy {
        MovesAdapter(this)
    }

    private val abilitiesAdapter by lazy {
        AbilitiesAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        listAbilities.adapter = abilitiesAdapter
         listMoves.adapter = movesAdapter
        val id = intent?.extras?.get("id") as String
        getPokemonDetail(id)
    }

    override fun onResume() {
        super.onResume()
        val id = intent?.extras?.get("id") as String
        getPokemonDetail(id)
    }



    private fun getPokemonDetail(id: String){
        progress.visibility = View.VISIBLE
        pokemonViewModel.pokemon(id).observe(this, Observer {
            movesAdapter.updateList(it.moves)
            abilitiesAdapter.updateList(it.abilities)
            name.text = it.name
            weights.text = it.weight
            heights.text = it.height
            Glide.with(this)
                .load(it.sprites?.front_default)
                .into(image)
            progress.visibility = View.GONE

        })
    }

}

