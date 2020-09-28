package com.lina.kotlinapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lina.kotlinapp.R
import com.lina.kotlinapp.model.ListPokemon
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(
    val context: Context,
    var response: MutableList<ListPokemon> = mutableListOf(),
    var onClickItem: (item: ListPokemon) -> Unit = {}
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {

        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_pokemon, parent, false)

        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = response[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = response.size

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var item: ListPokemon

        init {
            itemView.setOnClickListener {
                if (::item.isInitialized){
                    onClickItem(item)
                }
            }
        }


        fun bind(item: ListPokemon) {
            this.item = item
            itemView.name.text = item.name
        }
    }


    fun updateList(newPokemons: MutableList<ListPokemon>) {

        val diff = DiffUtil.calculateDiff(object: DiffUtil.Callback(){
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
                    = response[oldItemPosition] == newPokemons[newItemPosition]

            override fun getOldListSize(): Int = response.size

            override fun getNewListSize(): Int = newPokemons.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
                    = response[oldItemPosition].url == newPokemons[newItemPosition].url
        })

        this.response = newPokemons
        diff.dispatchUpdatesTo(this@PokemonAdapter)
    }

    fun addList(newPokemons: MutableList<ListPokemon>) {

        val diff = DiffUtil.calculateDiff(object: DiffUtil.Callback(){
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
                    = response[oldItemPosition] == newPokemons[newItemPosition]

            override fun getOldListSize(): Int = response.size

            override fun getNewListSize(): Int = newPokemons.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
                    = response[oldItemPosition].url == newPokemons[newItemPosition].url
        })

        this.response.addAll(newPokemons)
        diff.dispatchUpdatesTo(this@PokemonAdapter)
    }


}