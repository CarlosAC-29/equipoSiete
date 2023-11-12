package com.example.picobotella7.view

import android.content.ContentValues.TAG
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toolbar
import com.example.picobotella7.R
import com.example.picobotella7.view.fragment.HomeFragment

class MainActivity : AppCompatActivity() {
    var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reproduceSoundPool()
        framentoDinamico()
    }

    fun reproduceSoundPool(){
        mediaPlayer = MediaPlayer.create(this, R.raw.soundtrack)
        mediaPlayer?.start()
        mediaPlayer?.isLooping = true
    }

    private fun framentoDinamico(){
        val fragmentManager = supportFragmentManager
        val home = HomeFragment()
        fragmentManager.beginTransaction()
            .replace(R.id.homeFragment, home)
            .commit()
    }


}