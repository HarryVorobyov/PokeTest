package com.example.pokemon.ui.pokemonList.dialogDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.pokemon.R
import com.example.pokemon.databinding.PokeDetailsBinding
import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pokemon.ui.pokeDetails.PokeDetailsActivity

class DialogDetails : DialogFragment() {
    val KEY_POKE_ID = "poke_id"
    lateinit var binding: PokeDetailsBinding
    lateinit var pokeViewModel: DialogDetailsViewModel
    var showLoader = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflate(inflater, R.layout.poke_details, container, false)
        pokeViewModel = ViewModelProviders.of(this).get(DialogDetailsViewModel::class.java)

        binding.loader = true

        arguments?.let {
            pokeViewModel.setId(it.getInt(KEY_POKE_ID))
        }

        pokeViewModel.getData().observe(this,
            Observer { t ->
                binding.loader = false
                binding.pokeItem = t
            })


        binding.loader = showLoader

        return binding.root
    }

    companion object{
        fun newInstance(pokeId: Int):DialogFragment{
            val arg = Bundle();
            arg.putInt(PokeDetailsActivity.KEY_POKE_ID, pokeId)
            val dialg = DialogDetails()
            dialg.arguments = arg
            return dialg
        }
    }

}