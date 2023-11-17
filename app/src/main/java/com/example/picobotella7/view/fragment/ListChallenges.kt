package com.example.picobotella7.view.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.picobotella7.model.Challenge
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picobotella7.R
import com.example.picobotella7.databinding.ItemListBinding
import com.example.picobotella7.databinding.FragmentListChallengesBinding
import com.example.picobotella7.view.adapter.ChallengesAdapter
import com.example.picobotella7.view.dialog.DialogAdd
import com.example.picobotella7.view.viewholder.OnEditClickListener
import com.example.picobotella7.viewmodel.challengeViewModel



class ListChallenges : Fragment() , OnEditClickListener {
    private lateinit var binding: FragmentListChallengesBinding
    private lateinit var bindingItem: ItemListBinding
    private val challengeViewModel: challengeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListChallengesBinding.inflate(inflater,container,false)
        goHome()
        controladores()
        observadorViewModel()
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun observadorViewModel() {
        observerListChallenge()
    }

    private fun observerListChallenge() {
        challengeViewModel.getListChallenge()
        challengeViewModel.listInventory.observe(viewLifecycleOwner){ listChallenge ->
            val recycler = binding.recyclerview
            val layoutManager =LinearLayoutManager(context)
            recycler.layoutManager = layoutManager
            val adapter = ChallengesAdapter(listChallenge,this)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()

        }
    }

    private fun goHome(){
        binding.toolbarinclude.GoHomeButton.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
    private fun controladores() {
        dialogAdd()
    }

    private  fun dialogAdd(){
        binding.fbagregar.setOnClickListener {
            val dialog = DialogAdd(challengeViewModel){observerListChallenge()}
            dialog.showDialog(binding.root.context)
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = binding.toolbarinclude.toolbartext
        val toolbarTitle: TextView = toolbar.findViewById(R.id.toolbartext)
        toolbarTitle.text = "Retos"

    }


    override fun onDeleteClick(challengeId: Int) {
        // Lógica para manejar el clic en el botón de eliminar
        // Puedes mostrar un diálogo de confirmación para la eliminación aquí.
        dialogDelete(challengeId){observerListChallenge()}
    }

    private fun dialogDelete(challengeId: Int, afterDeleteCallback: () -> Unit) {
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.layout_delete_dialog, null)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)

        val alertDialog = dialogBuilder.create()
        alertDialog.setCancelable(false)
        val textDescription = dialogView.findViewById<TextView>(R.id.TextDescription)
        textDescription.text = challengeViewModel.selectChallenge(challengeId)?.challengetext?: "No description available"

        val btnConfirmDelete = dialogView.findViewById<Button>(R.id.buttonSi)
        val btnCancelDelete = dialogView.findViewById<Button>(R.id.buttonNo)

        btnConfirmDelete.setOnClickListener {
            // Lógica para eliminar el desafío
            challengeViewModel.deleteChallenge(challengeId)
            alertDialog.dismiss()
            // Llamada al callback después de cerrar el diálogo
            afterDeleteCallback.invoke()
        }

        btnCancelDelete.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.show()
    }
    override fun onEditClick(challengeId: Int) {
        println("Edit Clicked for Challenge ID: $challengeId")
        showEditDialog(challengeId){observerListChallenge()}
    }

    @SuppressLint("MissingInflatedId")
    private fun showEditDialog(challengeId: Int, afterUpdateCallback: () -> Unit) {


        val viewModel = challengeViewModel

        val inflater = LayoutInflater.from(requireContext())
        val customLayout = inflater.inflate(R.layout.layout_update_dialog, null)

        val textDescription = customLayout.findViewById<EditText>(R.id.editTextDescription)
        val buttonSave = customLayout.findViewById<Button>(R.id.buttonSalvar)
        val buttonCancel = customLayout.findViewById<Button>(R.id.buttonCancelar)

        val alertDialog = AlertDialog.Builder(requireContext()).create()
        alertDialog.setCancelable(false)
        alertDialog.setView(customLayout)
        val actualChallenge = viewModel.selectChallenge(challengeId)
        textDescription.setText(actualChallenge?.challengetext)
        buttonSave.setOnClickListener {
            val newDescription = textDescription.text.toString()
            actualChallenge?.let {
                viewModel.updateChallenge(it.id, newDescription)

            }

            alertDialog.dismiss()
            afterUpdateCallback.invoke()
        }

        buttonCancel.setOnClickListener {
            // Handle cancel button click
            alertDialog.dismiss()
        }

        // Show the AlertDialog
        alertDialog.show()
    }


}


