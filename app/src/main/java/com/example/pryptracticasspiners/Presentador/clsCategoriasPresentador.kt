package com.example.pryptracticasspiners.Presentador

import com.example.pryptracticasspiners.modelo.clsCategoriasModelo
import com.example.pryptracticasspiners.modelo.dataCategorias
import com.example.pryptracticasspiners.vista.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsCategoriasPresentador(val vista: MainActivity) {
    private val modelo = clsCategoriasModelo()
    private var listaCategorias: List<dataCategorias> = emptyList()

    // Nota: Asegúrate de pasar tu interfaz 'vista' por el constructor de esta clase
    // si aún no lo has hecho (ej. class clsCategoriasPresentador(private val vista: ContratoCategorias.Vista) { ... })

    fun iniciar() {
        // Se usa un Scope simple para no bloquear la interfaz
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Aquí recibes el RETURN directo del modelo
                listaCategorias = modelo.obtenerCategorias()
                vista.mostrarCategorias(listaCategorias)

            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarCategoria(posicion: Int) {
        if (posicion > 0 && posicion < listaCategorias.size) {
            val categoria = listaCategorias[posicion]
            vista.mostrarMensajeExito(categoria.id, categoria.categoria)
        }
    }
}