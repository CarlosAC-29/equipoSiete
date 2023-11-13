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
import com.example.picobotella7.R

class DialogAdd {
    companion object {
        fun showDialog(context: Context): AlertDialog {
            val builder = AlertDialog.Builder(context)
            builder.setCancelable(false)

            // Crear un diseño personalizado para el diálogo
            val view = LayoutInflater.from(context).inflate(R.layout.layout_add_dialog, null)
            val editText = view.findViewById<EditText>(R.id.editTextDescription)
            val saveButton = view.findViewById<Button>(R.id.buttonSave)

            // Deshabilitar el botón "Guardar" al principio
            saveButton.isEnabled = false

            // Habilitar el botón "Guardar" cuando se ingrese texto
            editText.addTextChangedListener(object: TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                    TODO("Not yet implemented")
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    TODO("Not yet implemented")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (editText.length()>0){saveButton.isEnabled= true
                        saveButton.setBackgroundColor(Color.parseColor("#FF3D00"))
                    }


                }
            })


            builder.setView(view)
                .setTitle("Agregar Reto")
                .setPositiveButton("Guardar") { dialog, _ ->
                    val userInput = editText.text.toString()
                    Toast.makeText(context, "Texto ingresado: $userInput", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    Toast.makeText(context, "Cancelar", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }

            return builder.create()
        }
    }
}