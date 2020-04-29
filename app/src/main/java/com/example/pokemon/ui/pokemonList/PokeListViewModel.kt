package com.example.pokemon.ui.pokemonList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PagedList.Config.Builder
import com.example.pokemon.App
import com.example.pokemon.model.PokeModel
import com.example.pokemon.service.PokeDataSourceFactory
import com.example.pokemon.service.RetrofitInstance
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class PokeListViewModel(application: Application) : AndroidViewModel(application) {
    private var executor: Executor = Executors.newFixedThreadPool(2)//TODO
    private val pokePagedList: LiveData<PagedList<PokeModel>>
    private var factory: PokeDataSourceFactory = PokeDataSourceFactory(App.getRep().movieDataService, application)

    init {
        val config: PagedList.Config = Builder()
            .setEnablePlaceholders(true)//TODO
            .setInitialLoadSizeHint(20)//TODO
            .setPageSize(20)//TODO
            .setPrefetchDistance(4)//TODO
            .build()//TODO

        pokePagedList = ((LivePagedListBuilder(factory, config))
            .setFetchExecutor(executor).build())
    }

    fun getPokePagedList() = pokePagedList
}