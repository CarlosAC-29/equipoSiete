package com.example.picobotella7.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picobotella7.R
import com.example.picobotella7.databinding.FragmentGameInstructionsBinding
import com.example.picobotella7.databinding.FragmentListChallengesBinding
import com.example.picobotella7.model.Challenge
import com.example.picobotella7.view.adapter.ChallengesAdapter
import com.example.picobotella7.view.dialog.DialogAdd.Companion.showDialog


class ListChallenges : Fragment() {
    private lateinit var binding: FragmentListChallengesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListChallengesBinding.inflate(inflater,container,false)
        goHome()
        controladores()
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun goHome(){
        binding.toolbarinclude.GoHomeButton.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
    private fun controladores() {
        dialogAdd()

    }
    private fun recycler() {

        var listaRetos= mutableListOf( Challenge(3,"holaaaaa 1"),
            Challenge(4,"holaaaaa 2"),
            Challenge(5,"holaaaaa 3"),
            Challenge(6,"holaaaaa 4"),
            Challenge(7,"holaaaaa 5"),
            Challenge(8,"holaaaaa 6"),
            Challenge(9,"holaaaaa 7"),
            Challenge(10,"holaaaaa 8"),)

        val recycler= binding.recyclerview
        recycler.layoutManager= LinearLayoutManager(context)
        val adapter =ChallengesAdapter(listaRetos)
        recycler.adapter=adapter
        adapter.notifyDataSetChanged()
    }
    private  fun dialogAdd(){
        binding.fbagregar.setOnClickListener {
            showDialog(binding.root.context)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = binding.toolbarinclude.toolbartext
        val toolbarTitle: TextView = toolbar.findViewById(R.id.toolbartext)
        toolbarTitle.text = "Retos"

    }
}