package com.example.pryptracticasspiners.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pryptracticasspiners.Adaptador.ProductosAdaptador
import com.example.pryptracticasspiners.Presentador.clsCategoriasPresentador
import com.example.pryptracticasspiners.R
import com.example.pryptracticasspiners.modelo.dataCategorias
import com.example.pryptracticasspiners.modelo.dataProductos

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: clsCategoriasPresentador
    private lateinit var spinnerCategorias: Spinner
    private lateinit var recyclerProductos: RecyclerView
    private lateinit var adapterProductos: ProductosAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinnerCategorias = findViewById(R.id.spnCategorias)
        recyclerProductos = findViewById(R.id.rvLista)

        recyclerProductos.layoutManager = LinearLayoutManager(this)
        adapterProductos = ProductosAdaptador(this, emptyList())
        recyclerProductos.adapter = adapterProductos
        presenter = clsCategoriasPresentador(this)

        configurarListeners()
        presenter.iniciar()
    }
    private fun configurarListeners() {
        spinnerCategorias.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.seleccionarCategoria(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    fun mostrarCategorias(categorias: List<dataCategorias>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategorias.adapter = adapter
    }
    fun mostrarProductos(productos: List<dataProductos>) {
        adapterProductos.actualizarLista(productos)
    }
    fun seleccionarProducto(producto: dataProductos) {
        presenter.seleccionarProducto(producto)
    }
    fun abrirDetalleProducto(id: Int, nombre: String, descripcion: String, precio: Double) {
        val intent = Intent(this, DetalleProductoActivity::class.java)
        intent.putExtra("PRODUCTO_ID", id)
        intent.putExtra("NOMBRE_PRODUCTO", nombre)
        intent.putExtra("DESCRIPCION_PRODUCTO", descripcion) // <--- El nombre debe ser EXACTAMENTE "DESCRIPCION_PRODUCTO"
        intent.putExtra("PRECIO_PRODUCTO", precio)
        startActivity(intent)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}