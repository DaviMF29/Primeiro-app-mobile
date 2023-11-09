package com.example.sistemalogin.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemalogin.R
import com.example.sistemalogin.model.clubes

class AdapterFavorite(private val listaClubesFavs: ArrayList<clubes>): RecyclerView.Adapter<AdapterFavorite.FavoriteViewHolder>() {
        inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                val escudo = itemView.findViewById<ImageView>(R.id.escudo_clube)
                val nome = itemView.findViewById<TextView>(R.id.nome_clube)
                val desc = itemView.findViewById<TextView>(R.id.desc_clube)
                val qtdChamps = itemView.findViewById<TextView>(R.id.qntd_champions)
                val favorited = itemView.findViewById<ImageView>(R.id.img_star)


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
            val clubeLista = LayoutInflater.from(parent.context).inflate(R.layout.fragment_favorites,parent,false)
            val holder = FavoriteViewHolder(clubeLista)
            return holder
        }

        override fun getItemCount(): Int = listaClubesFavs.size


        override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
            holder.escudo?.setImageResource(listaClubesFavs[position].escudo)
            holder.nome?.text = listaClubesFavs[position].nome
            holder.desc?.text = listaClubesFavs[position].desc
            holder.qtdChamps?.text = listaClubesFavs[position].qtdChamps
            holder.favorited?.isClickable = listaClubesFavs[position].favorited

        }



        }








