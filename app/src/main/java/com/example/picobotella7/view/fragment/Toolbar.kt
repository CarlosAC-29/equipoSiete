package com.example.picobotella7.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.example.picobotella7.R


class Toolbar : Fragment() {
    private lateinit var instructionButtonView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("ToolbarFragment", "onCreateView called")
        val view = inflater.inflate(R.layout.fragment_toolbar, container, false)
        instructionButtonView = view.findViewById(R.id.instructionButtonView)

        return view

    }
    private fun navigationFragment() {
        instructionButtonView.setOnClickListener{
            Log.d("TAG","hola")
        }
    }


}