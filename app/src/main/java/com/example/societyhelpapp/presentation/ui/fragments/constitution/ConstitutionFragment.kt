package com.example.societyhelpapp.presentation.ui.fragments.constitution

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
import com.example.societyhelpapp.databinding.FragmentConstitutionBinding
import com.example.societyhelpapp.domain.repeatOnStarted
import com.example.societyhelpapp.presentation.ui.adapter.ConstitutionAdapter
import com.example.societyhelpapp.presentation.ui.fragments.constitution.model.ConstitutionAction
import com.example.societyhelpapp.presentation.ui.fragments.constitution.model.ConstitutionEvent
import com.example.societyhelpapp.presentation.ui.fragments.constitution.viewmodel.ConstitutionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class ConstitutionFragment : Fragment() {

    private lateinit var binding: FragmentConstitutionBinding

    private var adapter = ConstitutionAdapter()

    @Inject
    lateinit var viewModel: ConstitutionViewModel

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
        adapter.clickCallback = { subtitle ->
            fcSearchEditText.setText("")
            viewModel.obtainEvent(event = ConstitutionEvent.ItemClicked(infoConstitution = subtitle))
        }
        fcRVTopics.adapter = adapter
        fcRVTopics.layoutManager = LinearLayoutManager(context)
    }

    private fun setupStates() = binding.apply {
        viewModel.obtainEvent(event = ConstitutionEvent.LoadInformation)
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.constitutionInfo.collect {
                withContext(Dispatchers.Main) {
                    adapter.submitList(it)
                }
            }
        }
    }

    private fun setupActions() = binding.apply {
        fcSearchEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                fcSearchClear.visibility = if(text.toString() == "") View.GONE else View.VISIBLE
                viewModel.obtainEvent(event = ConstitutionEvent.FilterChanged(query = text.toString()))
            }

            override fun afterTextChanged(p0: Editable?) { }
        })
        fcSearchClear.setOnClickListener {
            fcSearchEditText.setText("")
        }
        repeatOnStarted {
            viewModel.constitutionAction.collect { action ->
                when(action) {
                    is ConstitutionAction.Navigate -> findNavController().navigate(R.id.constitutionInfoFragment)
                    is ConstitutionAction.ListChanged -> adapter.submitList(action.constitutions)
                }
            }
        }
    }
}