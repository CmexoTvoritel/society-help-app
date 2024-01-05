package com.example.societyhelpapp.presentation.ui.fragments.main

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
import com.example.societyhelpapp.data.model.main.Topic
import com.example.societyhelpapp.databinding.FragmentMainBinding
import com.example.societyhelpapp.presentation.ui.adapter.TopicAdapter
import com.example.societyhelpapp.presentation.ui.fragments.main.model.UIAction
import com.example.societyhelpapp.presentation.ui.fragments.main.model.UIEvent
import com.example.societyhelpapp.presentation.ui.fragments.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var binding: FragmentMainBinding

    private var adapter = TopicAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        setupStates()
        setupActions()
    }

    private fun setupRV() = binding.apply {
        adapter.clickCallback = { topic ->
            amSearchEditText.setText("")
            viewModel.obtainEvent(viewEvent = UIEvent.OpenInformation(topic = topic))
        }
        mfRVTopics.adapter = adapter
        mfRVTopics.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupStates() = binding.apply {
        viewModel.obtainEvent(viewEvent = UIEvent.LoadInformation)
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.allInformation.collect {
                withContext(Dispatchers.Main) {
                    adapter.submitList(it)
                }
            }
        }
    }

    private fun listChanged(changedList: List<Topic>) {
        adapter.submitList(changedList)
    }

    private fun setupActions() = binding.apply {
        amSearchEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.obtainEvent(viewEvent = UIEvent.FilterChanged(query = text.toString()))
            }

            override fun afterTextChanged(p0: Editable?) { }

        })
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.actions.collect { action ->
                when(action) {
                    is UIAction.NavigateInformation -> findNavController().navigate(R.id.informationFragment)
                    is UIAction.ListChanged -> listChanged(changedList = action.topics)
                }
            }
        }
    }
}