package com.example.pokemon.ui.pokemonList.dialogDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.pokemon.R
import com.example.pokemon.databinding.PokeDetailsBinding
import com.example.pokemon.model.PokeDetailsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import com.example.pokemon.App
import com.example.pokemon.ui.pokeDetails.PokeDetailsActivity

class DialogDetails : DialogFragment() {
    val KEY_POKE_ID = "poke_id"
    lateinit var binding: PokeDetailsBinding
    var showLoader = true
    var call: Call<PokeDetailsModel?>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(inflater, R.layout.poke_details, container, false)
        binding.loader = showLoader
        arguments?.let {
            call = App.getRep().movieDataService.getPokemonDetails(it.getInt(KEY_POKE_ID))
            call?.enqueue(createInitialCalback(binding))
        }


        return binding.root
    }

    fun createInitialCalback(binding: PokeDetailsBinding) = object :
        Callback<PokeDetailsModel?> {
        override fun onFailure(call: Call<PokeDetailsModel?>, t: Throwable) { //TODO
        }

        override fun onResponse(
            call: Call<PokeDetailsModel?>,
            response: Response<PokeDetailsModel?>
        ) {
            if (response.isSuccessful) {
                response.body()?.let {
                    showLoader = false
                    binding.loader = showLoader
                    binding.pokeItem = response.body()
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        call?.let {
            it.cancel()
        }
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