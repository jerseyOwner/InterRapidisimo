package com.example.pruebatecnicainterrapidisimo.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.pruebatecnicainterrapidisimo.R
import com.example.pruebatecnicainterrapidisimo.data.local.Database
import com.example.pruebatecnicainterrapidisimo.data.local.Table
import com.example.pruebatecnicainterrapidisimo.databinding.DetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

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

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.detailsFragment_toolbarTitle)

        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        initializeAdapter()
    }

    private fun initializeViews() {
        title = binding.detailsFragmentTitle
        searchView = binding.detailsFragmentSearchView
        searchView!!.clearFocus()

        binding.detailsFragmentRecyclerView.adapter = tableItemAdapter
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return false
            }
        })
    }

    private fun getTableNames(): List<Table> {
        val tableList = mutableListOf<Table>()
        val database = Database(requireContext(), listOf())
        database.updateTables()
        for( table in database.getTableNames2() ) {
            tableList.add(Table(table))
        }
        return tableList
    }

    private fun initializeAdapter() {
        binding.detailsFragmentRecyclerView.adapter = DetailsFragmentRecyclerAdapter(getTableNames())
    }

    private fun filterList(text: String?) {
        val filteredList: MutableList<Table> = mutableListOf()

        for (table in getTableNames()) {
            text?.let {
                if (table.name.lowercase().contains(text.lowercase())) {
                    filteredList.add(table)
                }
            }
        }

        binding.detailsFragmentRecyclerView.adapter = DetailsFragmentRecyclerAdapter(filteredList)
    }
}