package com.example.pryptracticasspiners.Presentador

import com.example.pryptracticasspiners.vista.DetalleProductoActivity
import com.example.pryptracticasspiners.modelo.clsProductosModelo
import com.example.pryptracticasspiners.modelo.dataImagenesProducto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//Hola elimina esto
class clsDetalleProductoPresentador(private val vista: DetalleProductoActivity) {

    private val modelo = clsProductosModelo()

    fun cargarDetalle(productoId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Aquí se consume obtenerDetalleProducto del modelo
                val listaImagenes: List<dataImagenesProducto> = modelo.obtenerDetalleProducto(productoId)
                vista.mostrarImagenes(listaImagenes)
            } catch (e: Exception) {
                vista.mostrarError("Error al cargar detalle: " + e.localizedMessage)
            }
        }
    }
}