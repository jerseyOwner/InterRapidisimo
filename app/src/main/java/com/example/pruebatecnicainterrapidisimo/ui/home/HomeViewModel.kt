package com.example.pruebatecnicainterrapidisimo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnicainterrapidisimo.data.network.model.SchemeResponse
import com.example.pruebatecnicainterrapidisimo.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _homeFragmentState = MutableStateFlow<HomeFragmentState>(HomeFragmentState.Idle)
    var homeFragmentState: StateFlow<HomeFragmentState> = _homeFragmentState.asStateFlow()

    private val _schemeData = MutableStateFlow<SchemeResponse>(SchemeResponse(listOf()))
    var schemeData: StateFlow<SchemeResponse> = _schemeData.asStateFlow()

    fun getSchemeInfo() {
        viewModelScope.launch {
            _homeFragmentState.value = HomeFragmentState.Loading
            _homeFragmentState.value = try {
                val response = networkRepository.getSchemeInfo().body()
                HomeFragmentState.Success(response ?: listOf())
            } catch (e: IOException) {
                HomeFragmentState.Error
            }
            catch (e: HttpException) {
                HomeFragmentState.Error
            }
            catch (e: Exception) {
                HomeFragmentState.Error
            }
        }
    }
}