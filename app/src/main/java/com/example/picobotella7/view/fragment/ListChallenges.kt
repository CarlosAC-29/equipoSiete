package com.example.picobotella7.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.picobotella7.R
import com.example.picobotella7.databinding.FragmentGameInstructionsBinding
import com.example.picobotella7.databinding.FragmentListChallengesBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [ListChallenges.newInstance] factory method to
 * create an instance of this fragment.
 */
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

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = binding.toolbarinclude.toolbartext
        val toolbarTitle: TextView = toolbar.findViewById(R.id.toolbartext)
        toolbarTitle.text = "Retos"

    }
}