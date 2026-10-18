package com.example.pryptracticasspiners.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.pryptracticasspiners.R
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pryptracticasspiners.modelo.dataImagenesProducto

class GaleriaAdaptador(private var listaImagenes: List<dataImagenesProducto>): RecyclerView.Adapter<GaleriaAdaptador.GaleriaViewHolder>() {

    //Antes tenemos que hacer una clase holder
    class GaleriaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //Lo dejamos pendiente primero inflamos el layout
        val imgGaleria: ImageView = itemView.findViewById(R.id.imgGaleria)
    }

    //Estos tres se van a programar, son contratos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GaleriaViewHolder {
        //inflar el layout item_galeria.xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_galeria, parent, false)
        return GaleriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: GaleriaViewHolder, position: Int) {
        val imagen = listaImagenes[position]
        val baseUrl = "http://192.168.1.69/tienda_api/"
        val urlCompleta = baseUrl + imagen.urlimagen

        Glide.with(holder.itemView.context)
            .load( urlCompleta)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imgGaleria)
    }

    override fun getItemCount(): Int {
        return listaImagenes.size
    }
    fun actualizarLista(nuevaLista: List<dataImagenesProducto>)
    {
        listaImagenes = nuevaLista
        notifyDataSetChanged()
    }

}


