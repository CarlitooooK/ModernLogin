package com.example.creamlogin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.creamlogin.R
import com.example.creamlogin.models.Corto


class CortoAdapter(private var lista: List<Corto>) : RecyclerView.Adapter<CortoAdapter.CortoViewHolder>() {

    class CortoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val txtDescripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
        val txtGenero: TextView = itemView.findViewById(R.id.txtGenero)
        val txtSinopsis: TextView = itemView.findViewById(R.id.txtSinopsis)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CortoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_titulo, parent, false)
        return CortoViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CortoViewHolder, position: Int) {
        val item = lista[position]
        holder.txtTitulo.text = item.tituloOriginal ?: "Sin título"
        holder.txtDescripcion.text = item.descripcion ?: "Sin descripción"
        holder.txtGenero.text = "Género: ${item.genero ?: "No definido"}"
        holder.txtSinopsis.text = item.sinopsis ?: "Sin sinopsis"
    }

    override fun getItemCount(): Int = lista.size


}


