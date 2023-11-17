package com.example.picobotella7.view.dialog

import android.R
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.picobotella7.databinding.DialogDareBinding

class DialogDare {
    companion object {
        fun showDialog(
            context: Context
        ) {
            val inflater = LayoutInflater.from(context)
            val binding = DialogDareBinding.inflate(inflater)

            val alertDialog = AlertDialog.Builder(context).create()
            alertDialog.window?.setBackgroundDrawableResource(R.color.transparent)
            alertDialog.setCancelable(false)
            alertDialog.setView(binding.root)

            binding.btnAceptar.setOnClickListener {
                alertDialog.dismiss()
            }
            alertDialog.show()
        }
    }
}