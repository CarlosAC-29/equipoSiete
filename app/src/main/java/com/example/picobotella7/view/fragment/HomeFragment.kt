package com.example.picobotella7.view.fragment

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.opengl.GLES30
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.example.picobotella7.R
import com.example.picobotella7.databinding.FragmentHomeBinding
import com.example.picobotella7.view.dialog.DialogAdd
import com.example.picobotella7.view.dialog.DialogDare
import com.example.picobotella7.viewmodel.PokemonViewModel
import com.example.picobotella7.viewmodel.challengeViewModel
import com.example.picobotella7.viewmodel.soundtrackViewModel
import com.example.picobotella7.databinding.DialogDareBinding


class HomeFragment : Fragment() {
    private val challengeViewModel: challengeViewModel by viewModels()
    private val pokemonViewModel: PokemonViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var pokeBinding: DialogDareBinding
    private lateinit var instructionButtonView: ImageView
    private lateinit var challengesButtonView: ImageView
    private lateinit var rateButtonView: ImageView
    private lateinit var soundButtonView: ImageView
    private lateinit var shareButtonView: ImageView
    private lateinit var lottieBottleAnimation: LottieAnimationView
    private var lastRotationDegrees = 0.0f
    private lateinit var numberCountDown: TextView
    val rotationDuration = 4000L // 4 segundos
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var soundtrack: MediaPlayer
    private val soundtrackViewModel: soundtrackViewModel by viewModels()
    private var length = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        lottieBottleAnimation = view.findViewById(R.id.lottieBottleAnimation)
        numberCountDown = view.findViewById(R.id.numberCountDown)
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.bottlesoundtrack)
        instructionButtonView = view.findViewById(R.id.instructionButtonView)
        challengesButtonView = view.findViewById(R.id.challengesButtonView)
        rateButtonView = view.findViewById(R.id.rateButtonView)
        soundButtonView = view.findViewById(R.id.soundButtonView)
        shareButtonView = view.findViewById(R.id.shareButtonView)
        goInstructions()
        goChallenges()
        startGame()
        goRate()
        muteSound()
        pokemonViewModel.getPokemon()
        goShare()
        soundtrack=MediaPlayer.create(requireContext(),R.raw.soundtrack)
        soundtrack.isLooping = true
        soundtrack.start()
        if (soundtrackViewModel.soundtrackEnabled.value==false){
            onStop()
        }


        return view
    }
    private fun animateButtonTouch(view: View) {
        val scaleAnimation = ScaleAnimation(
            1f, 0.8f, // fromX, toX
            1f, 0.8f, // fromY, toY
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        scaleAnimation.duration = 100 // Duración de la animación en milisegundos
        scaleAnimation.interpolator = AccelerateDecelerateInterpolator()
        scaleAnimation.repeatMode = Animation.REVERSE
        scaleAnimation.repeatCount = 1
        view.startAnimation(scaleAnimation)
    }

    private fun goShare() {
        shareButtonView.setOnClickListener {
            animateButtonTouch(shareButtonView)
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.nequi.MobileApp&hl=es_419&gl=es")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    private fun muteSound() {
        soundButtonView.setOnClickListener {

            if(soundtrack.isPlaying){
                soundtrackViewModel.setSoundtrackEnabled(enabled = false)
                onStop()
            }
            else
            {
                soundtrackViewModel.setSoundtrackEnabled(enabled = true)
                onResume()
            }
        }
    }

    private fun goRate() {
        rateButtonView.setOnClickListener {
            animateButtonTouch(rateButtonView)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.nequi.MobileApp&hl=es_419&gl=es"))
            startActivity(intent)
        }
    }

    private fun startGame(){
        lottieBottleAnimation.setOnClickListener {
            spinBottle()
            startCountdown()
        }
    }

    private fun goInstructions(){
        instructionButtonView.setOnClickListener{
            animateButtonTouch(instructionButtonView)
            findNavController().navigate(R.id.action_homeFragment2_to_gameInstructions)
        }
    }
    private fun goChallenges(){
        challengesButtonView.setOnClickListener{
            animateButtonTouch(challengesButtonView)
            findNavController().navigate(R.id.action_homeFragment2_to_listChallenges)
        }
    }

    private fun spinBottle() {

        // Ángulo de inicio y fin de la rotación
        val fromDegrees = lastRotationDegrees // Usar el ángulo final de la rotación anterior como inicio
        val toDegrees = (Math.random() * 3600).toFloat() + 360.0f // Giro de 1 a 10 vueltas

        // Crear la animación de rotación
        val rotateAnimation = RotateAnimation(
            fromDegrees, toDegrees,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnimation.duration = rotationDuration
        rotateAnimation.interpolator = AccelerateDecelerateInterpolator()
        rotateAnimation.fillAfter = true // Conservar la posición final después de la rotación
        rotateAnimation.isFillEnabled = true

        // Configurar el listener para guardar el ángulo final de la rotación actual
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                lottieBottleAnimation.visibility = View.GONE // Ocultar el botón antes de iniciar la animación
                mediaPlayer?.start()
                val stopSoundDelay = 4000L
                view?.postDelayed({
                    mediaPlayer?.pause()
                    mediaPlayer?.seekTo(0)
                }, stopSoundDelay)
            }

            override fun onAnimationEnd(animation: Animation?) {
                // Aquí puedes ejecutar código al final de la animación de rotación si es necesario
                val dare = DialogDare(challengeViewModel, pokemonViewModel)
                context?.let { dare.showDialog(it) }
                lastRotationDegrees = toDegrees
                lottieBottleAnimation.visibility = View.VISIBLE // Restaurar la visibilidad al finalizar la animación
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // Aquí puedes ejecutar código en cada repetición de la animación de rotación si es necesario
            }
        })

        // Iniciar la animación de rotación en la vista de la botella
        val bottleView = view?.findViewById<ImageView>(R.id.bottleView)
        bottleView?.startAnimation(rotateAnimation)
    }

    private fun startCountdown() {
        object : CountDownTimer(rotationDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                numberCountDown.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                numberCountDown.text = "0"
            }
        }.start()
    }

    override fun onStop() {
        super.onStop()
        if (soundtrack.isPlaying){
            animateButtonTouch(soundButtonView)
            soundtrack.pause()
            soundButtonView.setImageResource(R.drawable.baseline_volume_off_24)
        }
    }

    override fun onResume() {
        super.onResume()
        if (soundtrackViewModel.soundtrackEnabled.value==true){
            animateButtonTouch(soundButtonView)
            soundtrack.start()
            soundButtonView.setImageResource(R.drawable.baseline_volume_up_24)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Liberar recursos del MediaPlayer al destruir el fragmento
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun dear() {

    }

}