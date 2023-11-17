package com.example.picobotella7.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id")
    val id:Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("img")
    val img: String
)

data class PokemonResponse(
    @SerializedName("pokemon")
    val pokemon: List<Pokemon>
)
