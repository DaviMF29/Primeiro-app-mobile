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

class AdapterClube(private val clubes: ArrayList<clubes>): RecyclerView.Adapter<AdapterClube.ClubeViewHolder>() {
    inner class ClubeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val escudo = itemView.findViewById<ImageView>(R.id.escudo_clube)
        val nome = itemView.findViewById<TextView>(R.id.nome_clube)
        val desc = itemView.findViewById<TextView>(R.id.desc_clube)
        val qtdChamps = itemView.findViewById<TextView>(R.id.qntd_champions)
        val favorited = itemView.findViewById<ImageView>(R.id.img_star)


        fun bind(clube: clubes) {


            favorited.setOnClickListener {
                // Altere o estado de favorited do seu objeto clube aqui
                clube.favorited = !clube.favorited
                starredClick(clube)


            }
        }
        fun starredClick(clube: clubes) {
            itemView.findViewById<ImageView>(R.id.img_star).setImageResource(
                if (clube.favorited) R.drawable.baseline_star_24
                else R.drawable.baseline_star_border_24
            )

            // Chame a função do ViewModel para atualizar o estado de favoritos do clube

        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubeViewHolder {
        val clubeLista = LayoutInflater.from(parent.context).inflate(R.layout.clubes,parent,false)
        val holder = ClubeViewHolder(clubeLista)
        return holder

    }


    override fun getItemCount(): Int = clubes.size


    override fun onBindViewHolder(holder: ClubeViewHolder, position: Int) {
        holder.escudo.setImageResource(clubes[position].escudo)
        holder.nome.text = clubes[position].nome
        holder.desc.text = clubes[position].desc
        holder.qtdChamps.text = clubes[position].qtdChamps
        holder.favorited.isClickable = clubes[position].favorited
        holder.bind(clubes[position])
    }





}