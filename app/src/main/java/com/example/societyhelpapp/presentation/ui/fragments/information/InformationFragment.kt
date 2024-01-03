package com.example.societyhelpapp.presentation.ui.fragments.information

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhelpapp.databinding.FragmentInformationBinding
import com.example.societyhelpapp.presentation.ui.adapter.InformationAdapter
import com.example.societyhelpapp.presentation.ui.fragments.information.model.InformationModel
import com.example.societyhelpapp.presentation.ui.fragments.information.model.InformationUIAction
import com.example.societyhelpapp.presentation.ui.fragments.information.model.InformationUIEvent
import com.example.societyhelpapp.presentation.ui.fragments.information.viewmodel.InformationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class InformationFragment : Fragment() {

    private lateinit var binding: FragmentInformationBinding

    @Inject
    lateinit var viewModel: InformationViewModel

    private var adapter = InformationAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        setupState()
        setupActions()
    }

    private fun setupState() = binding.apply {
        viewModel.obtainEvent(viewEvent = InformationUIEvent.LoadInformation)
    }

    private fun setupRV() = binding.apply {
        fiRVInformation.adapter = adapter
        fiRVInformation.layoutManager = LinearLayoutManager(context)
    }

    private fun setupActions() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.actions.collect { action ->
                when(action) {
                    is InformationUIAction.LoadInformation -> loadInformation(
                        information = action.information
                    )
                    is InformationUIAction.LoadTitle -> loadTitle(
                        title = action.title
                    )
                }
            }
        }
    }

    private fun loadInformation(information: List<InformationModel>) = binding.apply {
        adapter.submitList(information)
    }

    private fun loadTitle(title: String) = binding.apply {
        fiTitleTopic.text = title
    }
}