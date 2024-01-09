package com.example.societyhelpapp.presentation.ui.fragments.functions

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhelpapp.R
import com.example.societyhelpapp.databinding.FragmentFunctionBinding
import com.example.societyhelpapp.domain.repeatOnStarted
import com.example.societyhelpapp.presentation.ui.adapter.FunctionsAdapter
import com.example.societyhelpapp.presentation.ui.fragments.functions.model.FunctionAction
import com.example.societyhelpapp.presentation.ui.fragments.functions.model.FunctionEvent
import com.example.societyhelpapp.presentation.ui.fragments.functions.model.topic.FunctionModel
import com.example.societyhelpapp.presentation.ui.fragments.functions.viewmodel.FunctionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FunctionFragment : Fragment() {

    private lateinit var binding: FragmentFunctionBinding

    private val adapter = FunctionsAdapter()

    @Inject
    lateinit var viewModel: FunctionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFunctionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        setupState()
        setupActions()
    }

    private fun setupRV() = binding.apply {
        adapter.clickCallback = { item ->
            ffSearchEditText.setText("")
            viewModel.obtainEvent(event = FunctionEvent.GoToInformation(item = item))
        }
        ffRVTopics.adapter = adapter
        ffRVTopics.layoutManager = LinearLayoutManager(context)
    }

    private fun setupState() = binding.apply {
        viewModel.obtainEvent(event = FunctionEvent.LoadInformation)
        repeatOnStarted {
            viewModel.information.collect { info ->
                adapter.submitList(info)
            }
        }
    }

    private fun setupActions() = binding.apply {
        ffSearchEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                ffSearchClear.visibility = if(text.toString() == "") View.GONE else View.VISIBLE
                viewModel.obtainEvent(event = FunctionEvent.SearchInformation(query = text.toString()))
            }

            override fun afterTextChanged(p0: Editable?) { }
        })
        ffSearchClear.setOnClickListener {
            ffSearchEditText.setText("")
        }
        repeatOnStarted {
            viewModel.actions.collect { action ->
                when(action) {
                    is FunctionAction.Navigate -> findNavController().navigate(R.id.functionInfoFragment)
                    is FunctionAction.ListChanged -> listChanged(functions = action.topics)
                }
            }
        }
    }

    private fun listChanged(functions: List<FunctionModel>) {
        adapter.submitList(functions)
    }
}