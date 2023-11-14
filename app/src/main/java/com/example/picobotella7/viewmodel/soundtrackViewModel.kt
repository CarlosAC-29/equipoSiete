package com.example.picobotella7.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class soundtrackViewModel(application: Application): AndroidViewModel(application) {
    private val _soundtrackEnabled = MutableLiveData<Boolean>().apply { value = true }
    val soundtrackEnabled: LiveData<Boolean> get() = _soundtrackEnabled

    fun setSoundtrackEnabled(enabled: Boolean) {
        _soundtrackEnabled.value = enabled
    }
}
