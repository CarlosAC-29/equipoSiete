package com.example.picobotella7.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import android.widget.Toast
import com.example.picobotella7.R

class ToolbarActionFragment : Fragment() {

    private lateinit var menu: View  // Cambiado a View en lugar de Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        menu = inflater.inflate(R.layout.toolbar_action, container, false)
        val view = menu.rootView

        // Obtener referencias a los botones
        val button1 = menu.findViewById<Button>(R.id.button1)
        val button2 = menu.findViewById<Button>(R.id.button2)
        val button3 = menu.findViewById<Button>(R.id.button3)
        val button4 = menu.findViewById<Button>(R.id.button4)
        val button5 = menu.findViewById<Button>(R.id.button5)

        // Configurar clics de los botones
        button1.setOnClickListener {
            showToast("Botón 1 clicado")
            Log.d("TAG", "ME PRESIONASTE HDTPM")
        }

        button2.setOnClickListener {
            showToast("Botón 2 clicado")
            // Agregar lógica adicional según sea necesario
        }

        button3.setOnClickListener {
            showToast("Botón 3 clicado")
            // Agregar lógica adicional según sea necesario
        }

        button4.setOnClickListener {
            showToast("Botón 4 clicado")
            // Agregar lógica adicional según sea necesario
        }

        button5.setOnClickListener {
            showToast("Botón 5 clicado")
            // Agregar lógica adicional según sea necesario
        }

        return view
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
