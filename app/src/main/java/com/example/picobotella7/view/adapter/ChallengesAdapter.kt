package com.example.picobotella7.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picobotella7.model.Challenge
import androidx.navigation.NavController
import com.example.picobotella7.databinding.ItemListBinding
import com.example.picobotella7.view.viewholder.ChallengeViewHolder
class ChallengesAdapter (private val  listChallenge:MutableList<Challenge>, private val navController: NavController):RecyclerView.Adapter<ChallengeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChallengeViewHolder(binding,navController)
    }



    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val fullList = listChallenge[position]
        holder.setChallenge(fullList)
    }

    override fun getItemCount(): Int {
        return listChallenge.size
    }


}