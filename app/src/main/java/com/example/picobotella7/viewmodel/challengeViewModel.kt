package com.example.picobotella7.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picobotella7.model.Challenge
import com.example.picobotella7.repository.ChallengeRepository
import kotlinx.coroutines.launch

class challengeViewModel(application: Application): AndroidViewModel(application){

    val context = getApplication<Application>()
    private val challengeRepository = ChallengeRepository (context)

    private val _listChallenge = MutableLiveData<MutableList<Challenge>>()
    val listInventory: LiveData<MutableList<Challenge>> get() = _listChallenge

    fun saveChallenge(challenge:Challenge){
        viewModelScope.launch {
            challengeRepository.saveChallenge(challenge)
        }

    }

    fun getListChallenge () {
        viewModelScope.launch {
            _listChallenge.value = challengeRepository.getListChallenge()
        }

    }


}