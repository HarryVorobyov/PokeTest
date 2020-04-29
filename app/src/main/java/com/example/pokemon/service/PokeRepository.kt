package com.example.pokemon.service

import android.app.Application
import androidx.paging.PageKeyedDataSource
import com.example.pokemon.App
import com.example.pokemon.model.PokeDetailsModel
import com.example.pokemon.model.PokeModel
import com.example.pokemon.model.ResponsePokeList
import com.example.pokemon.service.RetrofitInstance.Companion.getPokeService
import retrofit2.Callback

class PokeRepository(application:Application) {
    private var movieDataService = getPokeService()

    fun getPokemonDetails(pokeId:Int, callback: Callback<PokeDetailsModel?>){
        App.getRep().movieDataService.getPokemonDetails(pokeId).enqueue(callback)
    }

    fun getPokemonList(limit:Int, offset:Int, callback: Callback<ResponsePokeList?>){
        App.getRep().movieDataService.getPokeList(limit, offset).enqueue(callback)
    }
}