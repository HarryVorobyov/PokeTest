package com.example.pokemon.ui.pokemonList.dialogDetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pokemon.App
import com.example.pokemon.model.PokeDetailsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DialogDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private var data: MutableLiveData<PokeDetailsModel?> = MutableLiveData<PokeDetailsModel?>()
    private var pokeId: Int = -1

    fun setId(id:Int){
        if(id != -1){
            pokeId = id
            App.getRep().getPokemonDetails(pokeId, createInitialCalback())
        }
    }

    fun getData() = data

    fun createInitialCalback() = object :
        Callback<PokeDetailsModel?> {
        override fun onFailure(call: Call<PokeDetailsModel?>, t: Throwable) { //TODO
        }

        override fun onResponse(
            call: Call<PokeDetailsModel?>,
            response: Response<PokeDetailsModel?>
        ) {
            if (response.isSuccessful) {
                response.body()?.let {
                    data.value = response.body()
                }

            }
        }
    }
}