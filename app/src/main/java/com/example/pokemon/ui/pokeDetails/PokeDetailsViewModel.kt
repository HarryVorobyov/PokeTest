package com.example.pokemon.ui.pokeDetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokemon.App
import com.example.pokemon.model.PokeDetailsModel
import com.example.pokemon.service.PokeRepository
import com.example.pokemon.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private var pokeId: Int = -1
    private val pokeData: MutableLiveData<PokeDetailsModel?> = MutableLiveData<PokeDetailsModel?>()

    fun setId(id:Int){
        if(id != -1){
            pokeId = id
            App.getRep().movieDataService.getPokemonDetails(pokeId).enqueue(createInitialCalback(pokeData))
        }
    }

    fun getPokeData() = pokeData

    fun createInitialCalback(pokeData: MutableLiveData<PokeDetailsModel?>) = object :
        Callback<PokeDetailsModel?> {
        override fun onFailure(call: Call<PokeDetailsModel?>, t: Throwable) { //TODO
        }

        override fun onResponse(
            call: Call<PokeDetailsModel?>,
            response: Response<PokeDetailsModel?>
        ) {
            val body = response.body()
            if (response.isSuccessful){
                pokeData.value = response.body()
            }
        }
    }
}