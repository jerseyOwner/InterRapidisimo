package com.example.pruebatecnicainterrapidisimo.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.pruebatecnicainterrapidisimo.data.local.Table
import com.example.pruebatecnicainterrapidisimo.databinding.DetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val detailsViewModel by viewModels<DetailsViewModel>()

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private var title: TextView? = null
    private var searchView: SearchView? = null

    private val tableItemAdapter = DetailsFragmentRecyclerAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        observeState()
    }

    private fun initializeViews() {
        title = binding.detailsFragmentTitle
        searchView = binding.detailsFragmentSearchView
        searchView!!.clearFocus()

        binding.detailsFragmentRecyclerView.adapter = tableItemAdapter
        searchView!!.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return false
            }
        }
        )
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                detailsViewModel.tableList.collect{
                    binding.detailsFragmentRecyclerView.adapter = DetailsFragmentRecyclerAdapter(it)
                }
            }
        }
    }

    private fun filterList(text: String?) {
        val filteredList: MutableList<Table> = mutableListOf()

        for (table in detailsViewModel.tableList.value) {
            text?.let {
                if (table.name.lowercase().contains(text.lowercase())) {
                    filteredList.add(table)
                }
            }
        }

        binding.detailsFragmentRecyclerView.adapter = DetailsFragmentRecyclerAdapter(filteredList)
    }
}