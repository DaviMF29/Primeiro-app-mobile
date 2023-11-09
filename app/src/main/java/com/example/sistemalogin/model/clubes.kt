package com.example.sistemalogin.model

import com.example.sistemalogin.R

data class clubes(
    val id: Int,
    val escudo: Int,
    val nome: String,
    val desc:String,
    val qtdChamps : String,
    var favorited : Boolean

) {


}

class clubesBuilder{
    var id :Int =0
    var escudo :Int = 0
    var nome : String= ""
    var desc : String= ""
    var qtdChamps: String= ""
    var favorited: Boolean= false


    fun build():clubes = clubes(id,escudo, nome, desc, qtdChamps, favorited)
}

fun times(block:clubesBuilder.()->Unit) :clubes = clubesBuilder().apply(block).build()

fun fakeClubes(): List<clubes> = listOf(
    times {
        escudo = R.drawable.barcelona
        nome = "Dicas"
        desc = "Olá, user"
        qtdChamps = "29 jun"
        favorited = true
    },
    times {
        escudo = R.drawable.ajax
        nome = "Dicas"
        desc = "Olá, user"
        qtdChamps = "29 jun"
        favorited = false
    },
    times {
        escudo = R.drawable.city
        nome = "Dicas"
        desc = "Olá, user"
        qtdChamps = "29 jun"
        favorited = false
    },
)