package com.example.picobotella7.viewmodel

import com.example.picobotella7.repository.PokemonRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.picobotella7.model.Pokemon
import kotlinx.coroutines.launch

class PokemonViewModel (application: Application): AndroidViewModel(application) {
    val context = getApplication<Application>()
    private val pokemonRepository = PokemonRepository(context)
    private val _listPokemon = MutableLiveData<List<Pokemon>>()
    val listPokemon: LiveData<List<Pokemon>> = _listPokemon

    fun getPokemon() {
        viewModelScope.launch {
                _listPokemon.value = pokemonRepository.getPokemon().pokemon

        }
    }
}