package com.example.pokemon.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponsePokeList(
    @SerializedName("results")
    var results: ArrayList<PokeModel>

    /*,
    @SerializedName("count")
    private var count: Int,
    @SerializedName("next")
    private var next: String,
    @SerializedName("previous")
    private var previous: String*/
)