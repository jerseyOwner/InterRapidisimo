package com.example.pruebatecnicainterrapidisimo.ui.details

import androidx.lifecycle.ViewModel
import com.example.pruebatecnicainterrapidisimo.data.local.Table
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailsViewModel : ViewModel() {

    private val _tableList = MutableStateFlow<List<Table>>(listOf())
    val tableList: StateFlow<List<Table>> = _tableList.asStateFlow()

    init {
        getStoredTables()
    }

    private fun getStoredTables() {
        _tableList.value =
            listOf(
                Table("Tabla 1"), Table("Tabla 2"), Table("Tabla 3"),
                Table("Tabla 4"), Table("Tabla 5"), Table("Aire"),
                Table("Avión"), Table("Barco"), Table("Cordillera"),
                Table("Partido"), Table("Manzana"), Table("Jirafa"),
                Table("Dirección"), Table("Hola"), Table("Llamada")
            )
    }
}