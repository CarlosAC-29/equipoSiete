package com.example.picobotella7.repository

import android.app.Application
import com.example.picobotella7.model.Pokemon
import com.example.picobotella7.webservice.ApiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository(context: Application) {
    private var apiService = ApiUtils.getApiService()

    suspend fun getPokemon(): MutableList<Pokemon>{
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPokemon()
                response
            } catch (e: Exception) {
                e.printStackTrace()
                mutableListOf()
            }
        }
    }
}