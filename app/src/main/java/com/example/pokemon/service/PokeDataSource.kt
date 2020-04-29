package com.example.pokemon.service

import android.app.Application
import androidx.paging.PageKeyedDataSource
import com.example.pokemon.App
import com.example.pokemon.model.PokeModel
import com.example.pokemon.model.ResponsePokeList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeDataSource(val application: Application) :
    PageKeyedDataSource<Int, PokeModel>() {
    val LIMIT_ITEMS = 100

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PokeModel>
    ) {
        App.getRep().getPokemonList(LIMIT_ITEMS, LIMIT_ITEMS * 0, createInitialCalback(callback))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PokeModel>) {
        App.getRep().getPokemonList(LIMIT_ITEMS, LIMIT_ITEMS * params.key, createCalback(callback, params.key + 1))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PokeModel>) {}

    private fun createCalback(callback: LoadCallback<Int, PokeModel>, key: Int) = object :
        Callback<ResponsePokeList?> {
        override fun onFailure(call: Call<ResponsePokeList?>, t: Throwable) { //TODO
        }

        override fun onResponse(
            call: Call<ResponsePokeList?>,
            response: Response<ResponsePokeList?>
        ) {
            val body = response.body()
            callback.onResult(body!!.results, key)
        }
    }

    private fun createInitialCalback(callback: LoadInitialCallback<Int, PokeModel>) = object :
        Callback<ResponsePokeList?> {
        override fun onFailure(call: Call<ResponsePokeList?>, t: Throwable) { //TODO
        }

        override fun onResponse(
            call: Call<ResponsePokeList?>,
            response: Response<ResponsePokeList?>
        ) {
            val body = response.body()
            callback.onResult(body!!.results, null, 1)
        }
    }
}