package com.lina.kotlinapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lina.kotlinapp.R
import com.lina.kotlinapp.model.Abilities
import kotlinx.android.synthetic.main.item_detail.view.*

class AbilitiesAdapter(
    val context: Context,
    var response: MutableList<Abilities> = mutableListOf(),
    var onClickItem: (item: Abilities) -> Unit = {}
) : RecyclerView.Adapter<AbilitiesAdapter.AbilitiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesViewHolder {

        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_detail, parent, false)

        return AbilitiesViewHolder(view)
    }

    override fun onBindViewHolder(holder: AbilitiesViewHolder, position: Int) {
        val item = response[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = response.size

    inner class AbilitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var item: Abilities

        init {
            itemView.setOnClickListener {
                if (::item.isInitialized){
                    onClickItem(item)
                }
            }
        }


        fun bind(item: Abilities) {
            this.item = item
            itemView.name.text = item.ability?.name
        }
    }


    fun updateList(newPokemons: MutableList<Abilities>) {

        val diff = DiffUtil.calculateDiff(object: DiffUtil.Callback(){
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
                    = response[oldItemPosition] == newPokemons[newItemPosition]

            override fun getOldListSize(): Int = response.size

            override fun getNewListSize(): Int = newPokemons.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
                    = response[oldItemPosition].ability?.name == newPokemons[newItemPosition].ability?.name
        })

        this.response = newPokemons
        diff.dispatchUpdatesTo(this@AbilitiesAdapter)
    }


}