package com.example.pokemon.service

import android.app.Application
import com.example.pokemon.service.RetrofitInstance.Companion.getPokeService

class PokeRepository(application:Application) {
    var movieDataService = getPokeService()
}