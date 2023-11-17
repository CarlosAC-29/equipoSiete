package com.example.picobotella7.repository

import android.app.Application
import com.example.picobotella7.model.PokemonResponse
import com.example.picobotella7.webservice.ApiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository(context: Application) {
    private var apiService = ApiUtils.getApiService()

    suspend fun getPokemon(): PokemonResponse {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPokemon()
                response
            } catch (e: Exception) {
                e.printStackTrace()
                println("Entre")
                PokemonResponse(emptyList())
            }
        }
    }
}