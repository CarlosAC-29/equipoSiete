package com.example.picobotella7.view.dialog

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import com.example.picobotella7.R
import com.example.picobotella7.model.Challenge
import com.example.picobotella7.viewmodel.challengeViewModel
import androidx.fragment.app.viewModels

class DialogAdd (private val challengeViewModel: challengeViewModel, private val onClose: () -> Unit){

    fun showDialog(context: Context) {
        val builder = AlertDialog.Builder(context).create()

        builder.setCancelable(false)

        // Crear un diseño personalizado para el diálogo
        val view = LayoutInflater.from(context).inflate(R.layout.layout_add_dialog, null)
        val editText = view.findViewById<EditText>(R.id.editTextDescription)
        val saveButton = view.findViewById<Button>(R.id.buttonSave)
        val cancelButton = view.findViewById<Button>(R.id.buttonCancel)

        // Deshabilitar el botón "Guardar" al principio
        saveButton.isEnabled = false

        // Habilitar el botón "Guardar" cuando se ingrese texto
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                //
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("DialogAdd", "Texto cambiado: $s")
                if (editText.length() > 0) {
                    saveButton.isEnabled = true
                    saveButton.setBackgroundColor(Color.parseColor("#FF3D00"))
                } else {
                    saveButton.isEnabled = false
                    saveButton.setBackgroundColor(Color.parseColor("#A19D9C"))
                }
            }
        })
        builder.setView(view)

        fun saveChallenge() {
            val challengetext = editText.text.toString()

            val challenge = Challenge(challengetext = challengetext)
            challengeViewModel.saveChallenge(challenge)
            Log.d("test", challenge.toString())
            Toast.makeText(context, "Reto guardado!", Toast.LENGTH_SHORT).show()
            builder.dismiss()

        }

        cancelButton.setOnClickListener {
            builder.dismiss()
        }

        saveButton.setOnClickListener {
            saveChallenge()
            onClose.invoke()
        }

        builder.show()


    }
}
