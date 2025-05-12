/*
    - Fragmento de bienvenida que muestra el título del juego, una breve explicación y permite seleccionar la dificultad.
    - Navegación correcta al iniciar el juego.
    Josué Carlos Alberto Ramos Suyoc, 11/05/2025
*/
package com.example.colorsgame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.colorsgame.R
import com.example.colorsgame.databinding.FragmentWelcomeBinding
import com.example.colorsgame.utils.ColorGameUtils

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()        // Configurar el botón de inicio del juego
        binding.btnStartGame.setOnClickListener {
            // Obtener el nivel de dificultad seleccionado
            val difficulty = when {
                binding.rbHard.isChecked -> ColorGameUtils.DIFFICULTY_HARD
                binding.rbMedium.isChecked -> ColorGameUtils.DIFFICULTY_MEDIUM
                else -> ColorGameUtils.DIFFICULTY_EASY
            }

            // Navegar al fragmento del juego y pasar la dificultad como argumento
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToGameFragment(difficulty)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}