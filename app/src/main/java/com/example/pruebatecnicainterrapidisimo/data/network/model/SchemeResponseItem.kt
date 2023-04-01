package com.example.pruebatecnicainterrapidisimo.data.network.model


import com.squareup.moshi.Json

data class SchemeResponseItem(
    @Json(name = "BatchSize")
    val batchSize: Int,
    @Json(name = "Error")
    val error: String? = null,
    @Json(name = "FechaActualizacionSincro")
    val fechaActualizacion: String,
    @Json(name = "Filtro")
    val filtro: String,
    @Json(name = "MetodoApp")
    val metodoApp: String? = null,
    @Json(name = "NombreTabla")
    val nombreTabla: String,
    @Json(name = "NumeroCampos")
    val numeroCampos: Int,
    @Json(name = "Pk")
    val pk: String,
    @Json(name = "QueryCreacion")
    val queryCreacion: String
)