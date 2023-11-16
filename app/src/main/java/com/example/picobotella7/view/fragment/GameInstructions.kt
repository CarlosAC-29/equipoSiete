package com.example.picobotella7.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.picobotella7.R
import com.example.picobotella7.databinding.FragmentGameInstructionsBinding

class GameInstructions : Fragment() {

    private lateinit var binding: FragmentGameInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameInstructionsBinding.inflate(inflater, container, false)
        goHome()
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun goHome(){
        binding.toolbarinclude.GoHomeButton.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = binding.toolbarinclude.toolbartext
        val toolbarTitle:TextView = toolbar.findViewById(R.id.toolbartext)
        toolbarTitle.text = "Reglas del Juego"

    }
}
