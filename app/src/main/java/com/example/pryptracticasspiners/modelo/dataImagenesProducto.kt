package com.example.pryptracticasspiners.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataImagenesProducto(
    @SerialName(value= "id")val id:Int=0,
    @SerialName(value = "productos_id") val productosid:Int=0,
    @SerialName(value = "url_imagen") val urlimagen :String="",

)
