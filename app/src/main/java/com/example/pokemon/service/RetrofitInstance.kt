package com.example.pokemon.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private var retrofit: Retrofit? = null
        private val BASE_URL = "https://pokeapi.co/api/v2/"

        fun getPokeService(): PokeService {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client((OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY)).build()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }


            return retrofit!!.create(PokeService::class.java)
        }
    }
}
