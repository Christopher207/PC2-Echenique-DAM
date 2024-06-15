package com.example.pc2_echenique_dam

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pc2_echenique_dam.model.EquipoModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etEquipo: EditText = findViewById(R.id.etEquipo)
        val etAnio: EditText = findViewById(R.id.etAnio)
        val etTitulos: EditText = findViewById(R.id.etTitulos)
        val etImagen: EditText = findViewById(R.id.etImagen)
        val btnGuardar: Button = findViewById(R.id.btnGuardar)
        val db = FirebaseFirestore.getInstance()

        btnGuardar.setOnClickListener {
            val equipo = etEquipo.text.toString()
            val anio = etAnio.text.toString()
            val titulos = etTitulos.text.toString()
            val imagen = etImagen.text.toString()
            db.collection("Liga 1")
                .add(EquipoModel(equipo, anio, titulos, imagen))
                .addOnSuccessListener { documentReference ->
                    Log.d("Firebase", "Documento agregado con id: ${documentReference.id}")
                    Snackbar.make(it, "Equipo agregado", Snackbar.LENGTH_LONG).show()
                    val intent = Intent(this, ListadoActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener { e ->
                    Log.w("Firebase", "Error agregando documento", e)
                }
        }
    }
}