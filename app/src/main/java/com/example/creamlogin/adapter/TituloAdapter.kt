package com.example.creamlogin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.creamlogin.R
import com.example.creamlogin.models.Titulo

class TituloAdapter(private var lista: List<Titulo>) : RecyclerView.Adapter<TituloAdapter.TituloViewHolder>() {

    class TituloViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloTextView: TextView = itemView.findViewById(R.id.txtTitulo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TituloViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_titulo, parent, false)
        return TituloViewHolder(view)
    }

    override fun onBindViewHolder(holder: TituloViewHolder, position: Int) {
        val item = lista[position]
        holder.tituloTextView.text = item.tituloOriginal
    }

    override fun getItemCount(): Int = lista.size

}

