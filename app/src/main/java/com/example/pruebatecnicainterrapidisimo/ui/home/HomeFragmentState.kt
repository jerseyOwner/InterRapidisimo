package com.example.pruebatecnicainterrapidisimo.ui.home

import com.example.pruebatecnicainterrapidisimo.data.network.model.SchemeResponseItem

sealed class HomeFragmentState {
    data class Success(val schemeData: List<SchemeResponseItem>) : HomeFragmentState()
    object Loading : HomeFragmentState()
    object Error : HomeFragmentState()
    object Idle : HomeFragmentState()
}