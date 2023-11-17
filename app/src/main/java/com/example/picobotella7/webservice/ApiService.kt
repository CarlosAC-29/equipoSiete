package com.example.picobotella7.webservice

import com.example.picobotella7.model.PokemonResponse
import com.example.picobotella7.utils.Constants.END_POINT
import retrofit2.http.GET

interface ApiService {
    @GET(END_POINT)
    suspend fun getPokemon(): PokemonResponse
}