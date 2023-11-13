package com.example.picobotella7.view.fragment

import android.animation.ObjectAnimator
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.example.picobotella7.R

class HomeFragment : Fragment() {
    private lateinit var instructionButtonView: ImageView
    private lateinit var challengesButtonView: ImageView
    private lateinit var lottieBottleAnimation: LottieAnimationView
    private var lastRotationDegrees = 0.0f
    private lateinit var numberCountDown: TextView
    val rotationDuration = 4000L // 4 segundos
    private var mediaPlayer: MediaPlayer? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        lottieBottleAnimation = view.findViewById(R.id.lottieBottleAnimation)
        numberCountDown = view.findViewById(R.id.numberCountDown)
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.bottlesoundtrack)
        instructionButtonView = view.findViewById(R.id.instructionButtonView)
        challengesButtonView=view.findViewById(R.id.challengesButtonView)
        goInstructions()
        goChallenges()
        startGame()


        return view
    }

    private fun startGame(){
        lottieBottleAnimation.setOnClickListener {
            spinBottle()
            startCountdown()
        }
    }

    private fun goInstructions(){
        instructionButtonView.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment2_to_gameInstructions)
        }
    }
    private fun goChallenges(){
        challengesButtonView.setOnClickListener{
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

    override fun onDestroy() {
        super.onDestroy()
        // Liberar recursos del MediaPlayer al destruir el fragmento
        mediaPlayer?.release()
        mediaPlayer = null
    }

}