package com.example.pruebatecnicainterrapidisimo.data.repository

import com.example.pruebatecnicainterrapidisimo.data.network.NetworkDataSource
import com.example.pruebatecnicainterrapidisimo.data.network.model.SchemeResponseItem
import retrofit2.Response
import javax.inject.Inject

interface NetworkRepository {
    suspend fun getSchemeInfo(): Response<List<SchemeResponseItem>>
}

class NetworkRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
): NetworkRepository {
    override suspend fun getSchemeInfo(): Response<List<SchemeResponseItem>> {
        return networkDataSource.getSchemeInfo()
    }
}