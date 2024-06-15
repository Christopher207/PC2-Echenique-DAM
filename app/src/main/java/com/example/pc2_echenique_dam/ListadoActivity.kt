package com.example.pc2_echenique_dam

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pc2_echenique_dam.model.EquipoModel
import com.google.firebase.firestore.FirebaseFirestore

class ListadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnRegistro: Button = findViewById(R.id.btnNuevoRegistro)
        val rvEquipos: RecyclerView = findViewById(R.id.rvEquipos)
        var lstEquipos: List<EquipoModel>
        val db = FirebaseFirestore.getInstance()

        db.collection("Liga 1").addSnapshotListener { snap, error ->
            if (error != null) {
                Log.e("ListadoActivity", "Error al obtener datos de FIREBASE ERROR", error)
                return@addSnapshotListener
            }
            lstEquipos = snap!!.documents.map{document->
                EquipoModel(
                    document["equipo"].toString(),
                    document["anio"].toString(),
                    document["titulos"].toString(),
                    document["imagen"].toString()
                )
            }
            rvEquipos.adapter = EquipoAdapter(lstEquipos)
            rvEquipos.layoutManager = LinearLayoutManager(this)
        }

        btnRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }


    }
}