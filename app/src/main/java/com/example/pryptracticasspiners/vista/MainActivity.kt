package com.example.pryptracticasspiners.vista

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
import com.example.pryptracticasspiners.Presentador.clsCategoriasPresentador
import com.example.pryptracticasspiners.R
import com.example.pryptracticasspiners.modelo.dataCategorias

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: clsCategoriasPresentador
    private lateinit var spinnerCategorias: Spinner

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
        presenter = clsCategoriasPresentador(this) // Asegúrate de pasar 'this' como la vista si tu presentador lo requiere

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

    fun mostrarMensajeExito(id: Int, nombre: String) {
        Toast.makeText(this, "ID: " + id.toString() + " - Seleccionaste: " + nombre, Toast.LENGTH_SHORT).show()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}