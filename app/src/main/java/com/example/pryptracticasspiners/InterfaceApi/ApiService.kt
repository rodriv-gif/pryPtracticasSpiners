package com.example.pryptracticasspiners.InterfaceApi

import com.example.pryptracticasspiners.modelo.dataCategorias
import retrofit2.http.GET

interface ApiService {
    @GET(value="obtener_categorias.php")
    suspend fun obtenerCategorias(): List<dataCategorias>
}