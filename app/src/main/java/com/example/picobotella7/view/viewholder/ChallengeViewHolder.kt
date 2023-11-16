package com.example.picobotella7.view.viewholder


import android.os.Bundle
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.picobotella7.R
import com.example.picobotella7.databinding.ItemListBinding
import com.example.picobotella7.model.Challenge
import com.example.picobotella7.view.dialog.DialogDelete
import com.example.picobotella7.viewmodel.challengeViewModel

class ChallengeViewHolder(binding: ItemListBinding, private val navController: NavController): RecyclerView.ViewHolder(binding.root) {
    private val bindingItem = binding


    fun setChallenge(challenge: Challenge) {
        bindingItem.challengeText.text = challenge.challengetext



        bindingItem.icDelete.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("clave", challenge)
            println(challenge)
            navController.navigate(R.id.action_listChallenges_to_acctionFragment, bundle)
        //    val dialog = DialogDelete(challengeViewModel= challengeViewModel ,challenge)
        //    dialog.showDialog(bindingItem.icDelete.context)

        }

    }

}