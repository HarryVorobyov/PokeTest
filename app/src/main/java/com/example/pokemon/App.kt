package com.example.pokemon

import android.app.Application
import com.example.pokemon.service.PokeRepository

class App: Application(){


    override fun onCreate() {
        super.onCreate()
        app = this
        rep = PokeRepository(app)
    }

    companion object {
        private lateinit var rep: PokeRepository

        fun getRep(): PokeRepository = rep

        lateinit var app: App
            internal set
    }
}