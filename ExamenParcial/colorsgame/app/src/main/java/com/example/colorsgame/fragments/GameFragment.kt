package com.example.colorsgame.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.colorsgame.R
import com.example.colorsgame.databinding.FragmentGameBinding
import com.example.colorsgame.utils.ColorGameUtils

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private val args: GameFragmentArgs by navArgs()
    private var timer: CountDownTimer? = null
    private var score = 0

    // Variables para el juego
    private var currentColorName = ""
    private var currentDisplayColorId = 0
    private var currentDisplayColorName = ""

    // Botones de colores
    private lateinit var colorButtons: Map<String, Button>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar el mapa de botones de colores
        colorButtons = mapOf(
            "red" to binding.btnRed,
            "green" to binding.btnGreen,
            "blue" to binding.btnBlue,
            "yellow" to binding.btnYellow,
            "purple" to binding.btnPurple,
            "orange" to binding.btnOrange
        )

        // Configurar los listeners de los botones
        setupButtonListeners()

        // Iniciar el juego
        startGame()
    }

    private fun setupButtonListeners() {
        colorButtons.forEach { (colorName, button) ->
            button.setOnClickListener {
                checkAnswer(colorName)
            }
        }
    }

    private fun startGame() {
        // Reiniciar la puntuación
        score = 0
        updateScoreDisplay()

        // Crear el temporizador del juego
        timer = ColorGameUtils.createGameTimer(
            ColorGameUtils.GAME_DURATION,
            ColorGameUtils.COUNTDOWN_INTERVAL,
            binding.tvTimer
        ) {
            // Finalizar el juego cuando el temporizador llega a cero
            gameOver()
        }

        // Iniciar el temporizador
        timer?.start()

        // Mostrar el primer color
        setNewColor()
    }

    private fun setNewColor() {
        // Generar un color aleatorio para mostrar
        currentColorName = ColorGameUtils.getRandomColor()

        // Obtener el ID del color y el texto a mostrar según la dificultad
        currentDisplayColorId = ColorGameUtils.COLORS[currentColorName]!!

        when (args.difficulty) {
            ColorGameUtils.DIFFICULTY_EASY -> {
                // Fácil: Mostrar un cuadrado del color (sin texto)
                binding.tvColorDisplay.text = ""
                binding.tvColorDisplay.setBackgroundColor(
                    ContextCompat.getColor(requireContext(), currentDisplayColorId)
                )
            }
            ColorGameUtils.DIFFICULTY_MEDIUM -> {
                // Medio: Mostrar el nombre del color en el mismo color
                currentDisplayColorName = currentColorName
                val textColorId = ColorGameUtils.COLORS[currentColorName]!!
                val stringResId = ColorGameUtils.COLOR_NAMES[currentColorName]!!

                binding.tvColorDisplay.text = getString(stringResId)
                binding.tvColorDisplay.setTextColor(
                    ContextCompat.getColor(requireContext(), textColorId)
                )
                binding.tvColorDisplay.setBackgroundColor(
                    ContextCompat.getColor(requireContext(), R.color.white)
                )
            }
            ColorGameUtils.DIFFICULTY_HARD -> {
                // Difícil: Efecto Stroop - Mostrar nombre de color en un color diferente
                do {
                    currentDisplayColorName = ColorGameUtils.getRandomColor()
                } while (currentDisplayColorName == currentColorName)

                val textColorId = ColorGameUtils.COLORS[currentColorName]!!
                val textStringResId = ColorGameUtils.COLOR_NAMES[currentDisplayColorName]!!

                binding.tvColorDisplay.text = getString(textStringResId)
                binding.tvColorDisplay.setTextColor(
                    ContextCompat.getColor(requireContext(), textColorId)
                )
                binding.tvColorDisplay.setBackgroundColor(
                    ContextCompat.getColor(requireContext(), R.color.white)
                )
            }
        }
    }

    private fun checkAnswer(selectedColor: String) {
        val isCorrect = selectedColor == currentColorName

        // Reproducir sonido según la respuesta
        if (isCorrect) {
            ColorGameUtils.playSound(requireContext(), R.raw.correct_sound)
            score++
            updateScoreDisplay()
            setNewColor()
        } else {
            ColorGameUtils.playSound(requireContext(), R.raw.wrong_sound)
        }
    }

    private fun updateScoreDisplay() {
        binding.tvScore.text = getString(R.string.score, score)
    }

    private fun gameOver() {
        // Reproducir sonido de fin de juego
        ColorGameUtils.playSound(requireContext(), R.raw.game_over_sound)

        // Guardar el récord si es necesario
        ColorGameUtils.saveHighScore(requireContext(), score, args.difficulty)

        // Navegar al fragmento de resultados
        val action = GameFragmentDirections.actionGameFragmentToResultFragment(score, args.difficulty)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Cancelar el temporizador para evitar fugas de memoria
        timer?.cancel()
        timer = null
        _binding = null
    }
}