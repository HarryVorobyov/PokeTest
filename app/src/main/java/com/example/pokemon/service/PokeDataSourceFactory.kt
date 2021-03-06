package com.example.pokemon.service

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.pokemon.model.PokeModel


class PokeDataSourceFactory(application: Application): DataSource.Factory<Int, PokeModel>() {

    private var pokeDataSource = PokeDataSource(application)
    private var mutableLiveData: MutableLiveData<PokeDataSource> = MutableLiveData()

    override fun create():DataSource<Int, PokeModel>{
        mutableLiveData.postValue(pokeDataSource)
        return pokeDataSource
    }
}