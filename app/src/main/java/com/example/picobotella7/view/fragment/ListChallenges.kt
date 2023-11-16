package com.example.picobotella7.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picobotella7.R
import com.example.picobotella7.databinding.FragmentGameInstructionsBinding
import com.example.picobotella7.databinding.FragmentListChallengesBinding
import com.example.picobotella7.model.Challenge
import com.example.picobotella7.view.adapter.ChallengesAdapter
import com.example.picobotella7.view.dialog.DialogAdd
import com.example.picobotella7.viewmodel.challengeViewModel



class ListChallenges : Fragment() {
    private lateinit var binding: FragmentListChallengesBinding
    private val challengeViewModel: challengeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListChallengesBinding.inflate(inflater,container,false)
        goHome()
        controladores()
        observadorViewModel()
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun observadorViewModel() {
        observerListChallenge()
    }

    private fun observerListChallenge() {
        challengeViewModel.getListChallenge()
        challengeViewModel.listInventory.observe(viewLifecycleOwner){ listChallenge ->
            val recycler = binding.recyclerview
            val layoutManager =LinearLayoutManager(context)
            recycler.layoutManager = layoutManager
            val adapter = ChallengesAdapter(listChallenge)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()

        }
    }

    private fun goHome(){
        binding.toolbarinclude.GoHomeButton.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
    private fun controladores() {
        dialogAdd()
    }

    private  fun dialogAdd(){
        binding.fbagregar.setOnClickListener {
            val dialog = DialogAdd(challengeViewModel){observerListChallenge()}
            dialog.showDialog(binding.root.context)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = binding.toolbarinclude.toolbartext
        val toolbarTitle: TextView = toolbar.findViewById(R.id.toolbartext)
        toolbarTitle.text = "Retos"

    }
}