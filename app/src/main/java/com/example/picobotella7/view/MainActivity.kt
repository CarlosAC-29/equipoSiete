package com.example.picobotella7.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.picobotella7.R

class MainActivity : AppCompatActivity() {
    lateinit var binding ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(R.layout.activity_main)


    }
}