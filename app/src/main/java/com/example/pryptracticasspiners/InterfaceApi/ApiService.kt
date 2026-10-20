package com.example.pryptracticasspiners.InterfaceApi

import com.example.pryptracticasspiners.modelo.dataCategorias
import com.example.pryptracticasspiners.modelo.dataImagenesProducto
import com.example.pryptracticasspiners.modelo.dataProductos
import kotlinx.serialization.SerialName
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(value = "obtener_categorias.php")
    suspend fun obtenerCategorias(): List<dataCategorias>

    //Aqui agregamos los edpoint para el resicler y para la vista detalle
    @GET("obtener_productos.php")
    suspend fun obtenerProductos(@Query("categoria_id") categoriaId: Int): List<dataProductos>
    @GET("detalle_producto.php")
    suspend fun detalleProducto(@Query("producto_id") productoId: Int): List<dataImagenesProducto>

    @POST("login.php")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}