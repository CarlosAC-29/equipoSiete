package com.example.picobotella7.view.viewholder


import androidx.recyclerview.widget.RecyclerView
import com.example.picobotella7.databinding.ItemListBinding
import com.example.picobotella7.model.Challenge

interface OnEditClickListener {
    fun onEditClick(challengeId: Int)
    fun onDeleteClick(challengeId: Int)
}
class ChallengeViewHolder(binding: ItemListBinding, private val editClickListener:OnEditClickListener ): RecyclerView.ViewHolder(binding.root) {
    private val bindingItem = binding


    fun setChallenge(challenge: Challenge) {
        bindingItem.challengeText.text = challenge.challengetext

        bindingItem.icEdit.setOnClickListener {

            editClickListener.onEditClick(challenge.id)
        }

        bindingItem.icDelete.setOnClickListener {


                editClickListener.onDeleteClick(challenge.id)


        }

    }

}