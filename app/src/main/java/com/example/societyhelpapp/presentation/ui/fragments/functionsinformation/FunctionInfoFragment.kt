package com.example.societyhelpapp.presentation.ui.fragments.functionsinformation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhelpapp.databinding.FragmentFunctionInfoBinding
import com.example.societyhelpapp.domain.repeatOnStarted
import com.example.societyhelpapp.presentation.ui.adapter.FunctionsInfoAdapter
import com.example.societyhelpapp.presentation.ui.fragments.functionsinformation.model.FunctionInfoAction
import com.example.societyhelpapp.presentation.ui.fragments.functionsinformation.model.FunctionInfoEvent
import com.example.societyhelpapp.presentation.ui.fragments.functionsinformation.viewmodel.FunctionInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FunctionInfoFragment : Fragment() {

    private lateinit var binding: FragmentFunctionInfoBinding
    private val adapter = FunctionsInfoAdapter()

    @Inject
    lateinit var viewModel: FunctionInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFunctionInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        setupActions()
        setupState()
    }

    private fun setupRV() = binding.apply {
        ffiRVInformation.adapter = adapter
        ffiRVInformation.layoutManager = LinearLayoutManager(context)
    }

    private fun setupActions() {
        lifecycleScope.launch {
            viewModel.actions.collect { action ->
                when(action) {
                    is FunctionInfoAction.SetTitleInformation -> setTitle(title = action.title)
                    is FunctionInfoAction.SetDescriptionInformation -> setDescription(desc = action.descriptions)
                }
            }
        }
    }

    private fun setupState() {
        viewModel.obtainEvent(event = FunctionInfoEvent.LoadInformation)
    }

    private fun setTitle(title: String) = binding.apply {
        ffiTitleTopic.text = title
    }

    private fun setDescription(desc: List<String>) {
        adapter.submitList(desc)
    }
}