package com.example.picobotella7.view.viewholder

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.picobotella7.databinding.ItemListBinding
import com.example.picobotella7.model.Challenge

class ChallengeViewHolder(binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
    val bindingItem = binding
    fun setChallenge(challenge: Challenge) {
        bindingItem.challengeText.text = challenge.challengetext

    }

}