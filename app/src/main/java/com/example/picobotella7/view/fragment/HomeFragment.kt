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
import com.airbnb.lottie.LottieAnimationView
import com.example.picobotella7.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
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


        lottieBottleAnimation.setOnClickListener {
            Log.d("TAg","holla")
            spinBottle()
            startCountdown()
        }

        return view
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


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}