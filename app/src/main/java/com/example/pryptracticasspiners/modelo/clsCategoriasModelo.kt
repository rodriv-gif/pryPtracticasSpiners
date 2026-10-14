package com.example.pryptracticasspiners.modelo

class clsCategoriasModelo {
    suspend fun obtenerCategorias(): List<dataCategorias> {
        val datos = RetrofitCliente.apiService.obtenerCategorias() // Aquí hace el pedido a la BD

        val listaFinal = mutableListOf(dataCategorias(0, "Seleccione una categoría"))
        listaFinal.addAll(datos)

        return listaFinal // Retorna la lista directamente
    }
}
