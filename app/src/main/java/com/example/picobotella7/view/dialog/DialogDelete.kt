package com.example.picobotella7.view.dialog

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.picobotella7.model.Challenge

class DialogDelete(private val challengeViewModel: Challenge, private val challenge:Challenge ){
    fun showDialog(context: Context): AlertDialog {
        val builder = AlertDialog.Builder(context)

        builder.setCancelable(false)
        builder.setTitle("¿Deseas eliminar el siguiente reto?")
            .setMessage(challenge.challengetext)
            .setPositiveButton("SI") { dialog, _ ->
                Toast.makeText(context,"SI", Toast.LENGTH_SHORT).show()
                //challengeViewModel.deleteChallenge(challenge)
                dialog.dismiss()
            }
            .setNegativeButton("NO") { dialog, _ ->
                Toast.makeText(context,"NO", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        return builder.create()

    }
}
