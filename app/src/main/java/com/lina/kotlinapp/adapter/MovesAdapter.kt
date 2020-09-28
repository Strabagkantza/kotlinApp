package com.lina.kotlinapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lina.kotlinapp.R
import com.lina.kotlinapp.model.Moves
import kotlinx.android.synthetic.main.item_detail.view.*

class MovesAdapter(
    val context: Context,
    var response: MutableList<Moves> = mutableListOf(),
    var onClickItem: (item: Moves) -> Unit = {}
) : RecyclerView.Adapter<MovesAdapter.MovesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovesViewHolder {

        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_detail, parent, false)

        return MovesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovesViewHolder, position: Int) {
        val item = response[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = response.size

    inner class MovesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var item: Moves

        init {
            itemView.setOnClickListener {
                if (::item.isInitialized){
                    onClickItem(item)
                }
            }
        }


        fun bind(item: Moves) {
            this.item = item
            itemView.name.text = item.move?.name
        }
    }


    fun updateList(newPokemon: MutableList<Moves>) {

        val diff = DiffUtil.calculateDiff(object: DiffUtil.Callback(){
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
                    = response[oldItemPosition] == newPokemon[newItemPosition]

            override fun getOldListSize(): Int = response.size

            override fun getNewListSize(): Int = newPokemon.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
                    = response[oldItemPosition].move?.name == newPokemon[newItemPosition].move?.name
        })

        this.response = newPokemon
        diff.dispatchUpdatesTo(this@MovesAdapter)
    }


}