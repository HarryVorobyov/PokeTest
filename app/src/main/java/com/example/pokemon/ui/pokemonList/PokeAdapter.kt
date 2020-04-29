package com.example.pokemon.ui.pokemonList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.databinding.PokeItemBinding
import com.example.pokemon.model.PokeModel

class PokeAdapter :
    PagedListAdapter<PokeModel, PokeAdapter.PokeViewHolder>(PokeModel.CALLBACK) {
    private var listener: OnPokeClicListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        val movieListItemBinding: PokeItemBinding = inflate(
            LayoutInflater.from(parent.context),
            R.layout.poke_item,
            parent,
            false
        )

        return PokeViewHolder(movieListItemBinding)
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }


    class PokeViewHolder(var root: PokeItemBinding) : RecyclerView.ViewHolder(root.root) {
        fun bind(item: PokeModel?, listener: OnPokeClicListener?) {
            item?.let {
                root.pokeItem = it
                root.root.setOnClickListener {
                    listener?.let {
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            listener.onPokeClickListener(item.getId())
                        }
                    }
                }
                root.details.setOnClickListener {
                    listener?.let {
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            listener.onDetailsClickListener(item.getId())
                        }
                    }
                }
            }
        }
    }

    fun setListener(listener: OnPokeClicListener) {
        this.listener = listener
    }

    interface OnPokeClicListener {
        fun onPokeClickListener(pokeId: Int)
        fun onDetailsClickListener(pokeId: Int)
    }
}