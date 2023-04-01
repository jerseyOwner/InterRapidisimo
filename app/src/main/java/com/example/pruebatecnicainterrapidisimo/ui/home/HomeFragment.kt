package com.example.pruebatecnicainterrapidisimo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.pruebatecnicainterrapidisimo.R
import com.example.pruebatecnicainterrapidisimo.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel>()

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private var getInfoButton: Button? = null
    private var statusImage: ImageView? = null
    private var statusImageDescription: TextView? = null
    private var progressBar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        observeState()
    }

    private fun initializeViews() {
        getInfoButton = binding.homeFragmentGetInfoButton
        statusImage = binding.homeFragmentStatusImage
        statusImageDescription = binding.homeFragmentStatusImageDescription
        progressBar = binding.homeFragmentProgressBar

        getInfoButton?.setOnClickListener {
            homeViewModel.getSchemeInfo()
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.homeFragmentState.collect {
                    when(it) {
                        is HomeFragmentState.Loading -> {
                            progressBar?.visibility = View.VISIBLE
                            statusImage?.visibility = View.GONE
                            statusImageDescription?.visibility = View.GONE
                        }
                        is HomeFragmentState.Error -> {
                            progressBar?.visibility = View.GONE
                            statusImage?.apply {
                                visibility = View.VISIBLE
                                setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_failed_24, null))
                            }
                            statusImageDescription?.apply {
                                visibility = View.VISIBLE
                                text = getString(R.string.homeFragment_statusImage_description_error)
                            }
                        }
                        is HomeFragmentState.Idle -> {
                            progressBar?.visibility = View.GONE
                            statusImage?.apply {
                                visibility = View.VISIBLE
                                setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_info_24, null))
                            }
                            statusImageDescription?.apply{
                                visibility = View.VISIBLE
                                text = getString(R.string.homeFragment_statusImage_description_info)
                            }
                        }
                        is HomeFragmentState.Success -> {
                            progressBar?.visibility = View.GONE
                            statusImage?.apply {
                                visibility = View.VISIBLE
                                setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_done_24, null))
                            }
                            statusImageDescription?.apply{
                                visibility = View.VISIBLE
                                text = getString(R.string.homeFragment_statusImage_description_success)
                            }
                        }
                    }
                }
            }
        }
    }
}