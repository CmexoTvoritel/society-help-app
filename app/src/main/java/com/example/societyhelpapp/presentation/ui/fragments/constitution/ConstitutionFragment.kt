package com.example.societyhelpapp.presentation.ui.fragments.constitution

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.societyhelpapp.databinding.FragmentConstitutionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConstitutionFragment : Fragment() {

    private lateinit var binding: FragmentConstitutionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConstitutionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        setupStates()
        setupActions()
    }

    private fun setupRV() = binding.apply {

    }

    private fun setupStates() = binding.apply {

    }

    private fun setupActions() = binding.apply {

    }
}