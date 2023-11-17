package com.example.picobotella7.webservice

import com.example.picobotella7.data.Pokemon
import retrofit2.http.GET

data class PokedexResponse (
    val pokemon: List<Pokemon>
        )
interface PokemonApiService {
    @GET("pokedex.json")
    suspend fun getPokedex(): PokedexResponse
}

