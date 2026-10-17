package com.example.pryptracticasspiners.Presentador

import android.util.Log
import com.example.pryptracticasspiners.modelo.clsCategoriasModelo
import com.example.pryptracticasspiners.modelo.dataCategorias
import com.example.pryptracticasspiners.vista.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsCategoriasPresentador(val vista: MainActivity) {
    private val modelo = clsCategoriasModelo()
    private var listaCategorias: List<dataCategorias> = emptyList()

    fun iniciar() {
        // Se usa un Scope simple para no bloquear la interfaz
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Aquí recibes el RETURN directo del modelo
                listaCategorias = modelo.obtenerCategorias()
                vista.mostrarCategorias(listaCategorias)

            } catch (e: Exception) {
                Log.e("API_ERROR", "Error al conectar con la API: ${e.message}", e)
                vista.mostrarError("Error de conexión: " + (e.localizedMessage ?: e.message))
            }
        }
    }

    fun seleccionarCategoria(posicion: Int) {
        if (posicion >= 0 && posicion < listaCategorias.size) {
            val categoria = listaCategorias[posicion]
            vista.mostrarMensajeExito(categoria.id, categoria.categoria)
        }
    }
}
