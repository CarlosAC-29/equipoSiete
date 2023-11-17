package com.example.picobotella7.view.dialog

import android.R
import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.picobotella7.databinding.DialogDareBinding
import com.example.picobotella7.model.Pokemon
import com.example.picobotella7.viewmodel.PokemonViewModel
import com.example.picobotella7.viewmodel.challengeViewModel
import com.example.picobotella7.webservice.ApiUtils
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.sql.DataSource
import kotlin.random.Random

class DialogDare (private val challengeViewModel: challengeViewModel, private val pokemonViewModel: PokemonViewModel) {
        @SuppressLint("MissingInflatedId")
        fun showDialog(
            context: Context
        ) {
            val inflater = LayoutInflater.from(context)

            val binding = DialogDareBinding.inflate(inflater)
            val alertDialog = AlertDialog.Builder(context).create()

            val pokemones = pokemonViewModel.listPokemon.value
            println(pokemones)
            val pokemonSize = pokemonViewModel.listPokemon.value?.size
            val randomPossition = Random.nextInt(pokemonSize?:151)
            val pokemon: Pokemon? = pokemonViewModel.listPokemon.value?.get(randomPossition)

            val originalUrl = pokemon?.img
            val secureUrl = originalUrl?.replace("http://", "https://")

            println(pokemonViewModel.listPokemon.value?.get(2)?.name)
            println(pokemonViewModel.listPokemon.value?.size.toString())
            println(pokemon?.name)
            println(secureUrl)
            Glide.with(binding.root.context).load(secureUrl).into(binding.pokemonesImg)

                Glide.with(binding.root.context)
                    .load(secureUrl)
                    .listener(object: RequestListener<Drawable?>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Drawable?>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.e("Glide", "Image load failed: $e")
                            return false
                        }
                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Drawable?>?,
                            dataSource: com.bumptech.glide.load.DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.d("Glide", "Image load was successful")
                            return false
                        }
                    }).into(binding.pokemonesImg)


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