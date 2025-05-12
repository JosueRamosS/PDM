package com.example.colorsgame.utils

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import com.example.colorsgame.R

/**
 * Clase de utilidades para el juego de colores
 */
class ColorGameUtils {

    companion object {
        // Constantes para la dificultad
        const val DIFFICULTY_EASY = 0
        const val DIFFICULTY_MEDIUM = 1
        const val DIFFICULTY_HARD = 2

        // Constantes para el juego
        const val GAME_DURATION = 30000L // 30 segundos
        const val COUNTDOWN_INTERVAL = 1000L // 1 segundo

        // Lista de colores disponibles
        val COLORS = mapOf(
            "red" to R.color.red,
            "green" to R.color.green,
            "blue" to R.color.blue,
            "yellow" to R.color.yellow,
            "purple" to R.color.purple,
            "orange" to R.color.orange
        )

        // Lista de nombres de colores
        val COLOR_NAMES = mapOf(
            "red" to R.string.color_red,
            "green" to R.string.color_green,
            "blue" to R.string.color_blue,
            "yellow" to R.string.color_yellow,
            "purple" to R.string.color_purple,
            "orange" to R.string.color_orange
        )

        /**
         * Genera un color aleatorio de la lista de colores
         */
        fun getRandomColor(): String {
            return COLORS.keys.toList().random()
        }

        /**
         * Genera un nombre de color aleatorio para el efecto Stroop
         */
        fun getRandomColorName(): String {
            return COLOR_NAMES.keys.toList().random()
        }

        /**
         * Reproduce un sonido
         */
        fun playSound(context: Context, soundResId: Int) {
            MediaPlayer.create(context, soundResId).apply {
                setOnCompletionListener { it.release() }
                start()
            }
        }

        /**
         * Obtiene el récord actual del jugador
         */
        fun getHighScore(context: Context, difficulty: Int): Int {
            val sharedPrefs = getSharedPreferences(context)
            val key = "high_score_$difficulty"
            return sharedPrefs.getInt(key, 0)
        }

        /**
         * Guarda el récord del jugador si es mejor que el actual
         */
        fun saveHighScore(context: Context, score: Int, difficulty: Int) {
            val currentHighScore = getHighScore(context, difficulty)
            if (score > currentHighScore) {
                val sharedPrefs = getSharedPreferences(context)
                val editor = sharedPrefs.edit()
                editor.putInt("high_score_$difficulty", score)
                editor.apply()
            }
        }

        /**
         * Obtiene las SharedPreferences para guardar los récords
         */
        private fun getSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences("color_game_prefs", Context.MODE_PRIVATE)
        }

        /**
         * Crea un temporizador para el juego
         */
        fun createGameTimer(
            duration: Long,
            interval: Long,
            timerTextView: TextView,
            onFinish: () -> Unit
        ): CountDownTimer {
            return object : CountDownTimer(duration, interval) {
                override fun onTick(millisUntilFinished: Long) {
                    val secondsRemaining = millisUntilFinished / 1000
                    timerTextView.text = timerTextView.context.getString(
                        R.string.time_remaining,
                        secondsRemaining
                    )
                }

                override fun onFinish() {
                    timerTextView.text = timerTextView.context.getString(
                        R.string.time_remaining,
                        0
                    )
                    onFinish()
                }
            }
        }

        /**
         * Obtiene un mensaje para el resultado según la puntuación
         */
        fun getResultMessage(context: Context, score: Int): String {
            return when {
                score < 10 -> context.getString(R.string.result_message_poor)
                score < 20 -> context.getString(R.string.result_message_good)
                else -> context.getString(R.string.result_message_excellent)
            }
        }
    }
}