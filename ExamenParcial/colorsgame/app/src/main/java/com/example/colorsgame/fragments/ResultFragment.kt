/*
    - Fragmento de resultados que muestra el puntaje obtenido, el récord y un mensaje personalizado según la puntuación.
    - Navegación correcta para volver a jugar o regresar al menú principal.
    Josué Carlos Alberto Ramos Suyoc, 11/05/2025
*/
package com.example.colorsgame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.colorsgame.R
import com.example.colorsgame.databinding.FragmentResultBinding
import com.example.colorsgame.utils.ColorGameUtils

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mostrar la puntuación obtenida
        binding.tvResultScore.text = getString(R.string.result_score, args.score)

        // Obtener y mostrar el récord
        val highScore = ColorGameUtils.getHighScore(requireContext(), args.difficulty)
        binding.tvHighScore.text = getString(R.string.result_high_score, highScore)

        // Mostrar un mensaje según la puntuación
        binding.tvResultMessage.text = ColorGameUtils.getResultMessage(requireContext(), args.score)

        // Configurar el botón de jugar de nuevo
        binding.btnPlayAgain.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToGameFragment(args.difficulty)
            findNavController().navigate(action)
        }

        // Configurar el botón de volver al menú principal
        binding.btnMainMenu.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToWelcomeFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}