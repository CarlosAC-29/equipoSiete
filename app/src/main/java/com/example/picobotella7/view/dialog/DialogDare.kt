package com.example.picobotella7.view.dialog

import android.R
import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.picobotella7.databinding.DialogDareBinding
import com.example.picobotella7.viewmodel.challengeViewModel
import com.example.picobotella7.webservice.ApiUtils
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class DialogDare (private val challengeViewModel: challengeViewModel) {
        @SuppressLint("MissingInflatedId")
        fun showDialog(
            context: Context
        ) {
            val inflater = LayoutInflater.from(context)

            val binding = DialogDareBinding.inflate(inflater)
            val alertDialog = AlertDialog.Builder(context).create()

            alertDialog.window?.setBackgroundDrawableResource(R.color.transparent)
            alertDialog.setCancelable(false)
            binding.TextDare.text = challengeViewModel.ramdomChallenge().challengetext

            alertDialog.setView(binding.root)

            binding.btnAceptar.setOnClickListener {
                alertDialog.dismiss()
            }
            alertDialog.show()
        }
    }