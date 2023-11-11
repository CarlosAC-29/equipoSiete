package com.example.picobotella7.view

import android.content.ContentValues.TAG
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.picobotella7.R

class MainActivity : AppCompatActivity() {
    var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reproduceSoundPool()
    }

    fun reproduceSoundPool(){
        mediaPlayer = MediaPlayer.create(this, R.raw.soundtrack)
        mediaPlayer?.start()
        mediaPlayer?.isLooping = true
    }
}