package com.example.pokemon.service

import com.example.pokemon.model.PokeDetailsModel
import com.example.pokemon.model.ResponsePokeList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeService {
    @GET("pokemon")
    fun getPopularMovies(@Query("limit") limit: Int,
                         @Query("offset") page: Int): Call<ResponsePokeList?>

    @GET("pokemon/{pokeID}")
    fun getPokemonDetails(@Path(value = "pokeID", encoded = true) pokeID:Int): Call<PokeDetailsModel?>
}