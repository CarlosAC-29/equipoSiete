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
import com.example.picobotella7.databinding.FragmentAcctionBinding
import com.example.picobotella7.databinding.ItemListBinding
import com.example.picobotella7.databinding.FragmentGameInstructionsBinding

import com.example.picobotella7.model.Challenge
import com.example.picobotella7.view.adapter.ChallengesAdapter
import com.example.picobotella7.view.dialog.DialogAdd
import com.example.picobotella7.view.dialog.DialogDelete
import com.example.picobotella7.viewmodel.challengeViewModel

class AcctionFragment : Fragment() {
    private lateinit var binding: FragmentAcctionBinding

    private val challengeViewModel: challengeViewModel by viewModels()
    private lateinit var bindingItem: ItemListBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acction, container, false)
    }


}