package com.example.sistemalogin.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sistemalogin.R
import com.example.sistemalogin.model.clubes
import com.example.sistemalogin.model.clubesBuilder

class viewModel : ViewModel() {
    private val _timesFavoritos = MutableLiveData<MutableSet<clubes>>()
    val timesFavoritos: LiveData<MutableSet<clubes>> get() = _timesFavoritos
    val listaTimesFutebol: ArrayList<clubes> = arrayListOf(
        clubes(1,R.drawable.barcelona, "Barcelona",  "Descrição do Barcelona", "5 Champions", true),
        clubes(2,R.drawable.ajax, "Ajax",  "Descrição do Ajax", "4 Champions", false),
        clubes( 3,R.drawable.city, "Manchester City","Descrição do Manchester City", "3 Champions", false)
        // Adicione mais times conforme necessário
    )
    
    init {
        _timesFavoritos.value = mutableSetOf()
        // Adicione automaticamente os itens favoritados ao conjunto de favoritos ao iniciar
        listaTimesFutebol.forEach { clube ->
            if (clube.favorited) {
                _timesFavoritos.value?.add(clube)
            }
        }
    }

    fun toggleFavorito(clube: clubes) {
        val favoritos = _timesFavoritos.value ?: mutableSetOf()
        val item = listaTimesFutebol.find { it.id == clube.id }
        if (item != null && item.favorited) {
            favoritos.add(clube)
        } else {
            favoritos.remove(clube)
        }
        _timesFavoritos.value = favoritos
    }


    }

