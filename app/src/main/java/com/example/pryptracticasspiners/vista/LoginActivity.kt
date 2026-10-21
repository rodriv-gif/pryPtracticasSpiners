package com.example.pryptracticasspiners.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pryptracticasspiners.R
import android.widget.EditText
import com.example.pryptracticasspiners.Presentador.clsLoginPresentador
import android.widget.Button
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private lateinit var presenter: clsLoginPresentador
    private lateinit var txtUsuario: EditText
    private lateinit var txtClave: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // 1. Enlazar componentes XML
        txtUsuario = findViewById(R.id.txtUsuario)
        txtClave = findViewById(R.id.txtClave)
        btnLogin = findViewById(R.id.btnLogin)
        // 2. Inicializar Presentador
        presenter = clsLoginPresentador(this)
        // 3. Listener del botón
        btnLogin.setOnClickListener(this::ejecutarInicioSesion)
    }

    fun ejecutarInicioSesion(v: View) {
        val usuario = txtUsuario.text.toString().trim()
        val clave = txtClave.text.toString().trim()
        presenter.realizarLogin(usuario, clave)
    }

    fun loginExitoso(mensaje: String, nombreCliente: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    val intent = Intent(this, MainActivity::class.java)
    StarActivity(Intend)
    finish()
}
fun mostrarError(mensaje: String) {
    Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
}


