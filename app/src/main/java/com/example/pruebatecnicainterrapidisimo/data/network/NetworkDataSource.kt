package com.example.pruebatecnicainterrapidisimo.data.network

import com.example.pruebatecnicainterrapidisimo.data.network.model.SchemeResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface NetworkDataSource {

    @Headers(
        "usuario: admin"
    )
    @GET("ObtenerEsquema/true")
    suspend fun getSchemeInfo(): Response<List<SchemeResponseItem>>
}