package com.example.pc2_echenique_dam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pc2_echenique_dam.model.EquipoModel
import com.squareup.picasso.Picasso

class EquipoAdapter (private var lstEquipos: List<EquipoModel>):
    RecyclerView.Adapter<EquipoAdapter.EquipoViewHolder>(){
    class EquipoViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val ivImagenEquipo: ImageView = itemView.findViewById(R.id.ivImagenEquipo)
        val tvNombreEquipo: TextView = itemView.findViewById(R.id.tvNombreEquipo)
        val tvTitulosEquipo: TextView = itemView.findViewById(R.id.tvTitulosEquipo)
        val tvAnioEquipo: TextView = itemView.findViewById(R.id.tvAnioEquipo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EquipoViewHolder(layoutInflater.inflate(R.layout.item_equipo, parent, false))
    }

    override fun getItemCount(): Int {
        return lstEquipos.size
    }

    override fun onBindViewHolder(holder: EquipoViewHolder, position: Int) {
        val itemStadium = lstEquipos[position]
        holder.tvNombreEquipo.text = itemStadium.equipo.toString()
        holder.tvAnioEquipo.text = itemStadium.anio.toString()
        holder.tvTitulosEquipo.text = itemStadium.titulos.toString()
        Picasso.get().load(itemStadium.imagen.toString()).into(holder.ivImagenEquipo)
    }

}
