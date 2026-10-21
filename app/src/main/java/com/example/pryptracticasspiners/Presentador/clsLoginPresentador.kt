package com.example.pryptracticasspiners.Presentador

import android.util.Log.e
import android.view.View
import com.example.pryptracticasspiners.vista.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class clsLoginPresentador(private val view: LoginActivity) {

    private val modelo = clsLoginPresentador()

    fun realizarLogin(usuario: String, clave: String) {
        if (usuario.isEmpty() || clave.isEmpty()) {
            view.mostrarError("Por favor, ingresa usuario y contraseña")
            return
        }
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = withContext(Dispatchers.IO) {
                    modelo.autenticarUsuario(usuario, clave)
                    if (respuesta.exito) {
                        view.loginExitoso(respuesta .mensaje, respuesta.nombreCliente)
                    } else {
                        view.mostrarError(respuesta.mensaje)
                    }
                } catch  (e: Exception) run {
                    view("Error de conexión: ${e.localizedMessage ?: "Error desconocido"}")
                }
            }
        }
    }
}