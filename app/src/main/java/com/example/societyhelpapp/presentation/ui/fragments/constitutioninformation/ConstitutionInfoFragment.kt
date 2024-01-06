package com.example.societyhelpapp.presentation.ui.fragments.constitutioninformation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.societyhelpapp.databinding.FragmentConstitutionInformationBinding
import com.example.societyhelpapp.domain.repeatOnStarted
import com.example.societyhelpapp.presentation.ui.adapter.ConstitutionInfoAdapter
import com.example.societyhelpapp.presentation.ui.fragments.constitutioninformation.model.ConstitutionInfoAction
import com.example.societyhelpapp.presentation.ui.fragments.constitutioninformation.model.ConstitutionInfoEvent
import com.example.societyhelpapp.presentation.ui.fragments.constitutioninformation.viewmodel.ConstitutionInfoViewModel
import com.example.societyhelpapp.presentation.ui.fragments.information.model.InformationModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ConstitutionInfoFragment : Fragment() {

    private lateinit var binding: FragmentConstitutionInformationBinding

    private var adapter = ConstitutionInfoAdapter()

    @Inject
    lateinit var viewModel: ConstitutionInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConstitutionInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        setupActions()
        setupState()
    }

    private fun setupRV() = binding.apply {
        fciRVInformation.adapter = adapter
        fciRVInformation.layoutManager = LinearLayoutManager(context)
    }

    private fun setupActions() = binding.apply {
        lifecycleScope.launch {
            viewModel.actions.collect { action ->
                when(action) {
                    is ConstitutionInfoAction.SendInformation -> showInformation(information = action.information)
                    is ConstitutionInfoAction.LoadTitle -> loadTitle(title = action.title)
                }
            }
        }
    }

    private fun setupState() = binding.apply {
        viewModel.obtainEvent(viewEvent = ConstitutionInfoEvent.LoadInformation)
    }

    private fun showInformation(information: List<InformationModel>) {
        adapter.submitList(information)
    }

    private fun loadTitle(title: String) = binding.apply {
        fciTitleTopic.text = title
    }
}