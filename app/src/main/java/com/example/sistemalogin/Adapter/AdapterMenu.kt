package com.example.sistemalogin.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemalogin.R
import com.example.sistemalogin.model.opcoes

class AdapterMenu(private val opcoes : ArrayList<opcoes>):RecyclerView.Adapter<AdapterMenu.MenuViewHolder>() {
    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img_menu = itemView.findViewById<ImageView>(R.id.img_icon_menu)
        val settings = itemView.findViewById<TextView>(R.id.opcoes)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMenu.MenuViewHolder {
        val opcoesLista = LayoutInflater.from(parent.context).inflate(R.layout.opcoes,parent,false)
        val holder = MenuViewHolder(opcoesLista)
        return holder
    }

    override fun getItemCount():Int = opcoes.size

    override fun onBindViewHolder(holder: AdapterMenu.MenuViewHolder, position: Int) {
        holder.img_menu.setImageResource(opcoes[position].img_menu)
        holder.settings.text = opcoes[position].settings

    }

}