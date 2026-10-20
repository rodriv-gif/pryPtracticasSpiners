package com.example.pryptracticasspiners.modelo

class clsLoginModelo {
    suspend fun autenticarUsuario(usuario:String,clave:String):LoginResponse{
        val request = LoginRequest(nombreUsuario=usuario,passusuario=clave)
        return RetrofitCliente.apiService.iniciarSesion(request)
    }
}