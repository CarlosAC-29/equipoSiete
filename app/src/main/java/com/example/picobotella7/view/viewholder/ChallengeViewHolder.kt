package com.example.picobotella7.view.viewholder


import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.RecyclerView
import com.example.picobotella7.databinding.ItemListBinding
import com.example.picobotella7.model.Challenge

interface OnEditClickListener {
    fun onEditClick(challengeId: Int)
    fun onDeleteClick(challengeId: Int)
}
class ChallengeViewHolder(binding: ItemListBinding, private val editClickListener:OnEditClickListener ): RecyclerView.ViewHolder(binding.root) {
    private val bindingItem = binding

    private fun createClickAnimation(): Animation {
        val clickAnimation = ScaleAnimation(
            1.0f,
            0.9f,
            1.0f,
            0.9f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        clickAnimation.duration = 100 // Duración de la animación en milisegundos
        clickAnimation.fillAfter = true
        return clickAnimation
    }


    fun setChallenge(challenge: Challenge) {
        bindingItem.challengeText.text = challenge.challengetext

        bindingItem.icEdit.setOnClickListener {
            it.startAnimation(createClickAnimation())
            editClickListener.onEditClick(challenge.id)
        }

        bindingItem.icDelete.setOnClickListener {

            it.startAnimation(createClickAnimation())
            editClickListener.onDeleteClick(challenge.id)


        }

    }

}