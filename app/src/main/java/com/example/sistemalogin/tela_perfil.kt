package com.example.sistemalogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth

class tela_perfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_perfil)

        val logout = findViewById<AppCompatButton>(R.id.botao_sair)
        logout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, form_login::class.java)
            startActivity(intent)

            // Fecha a atividade atual para que o usuário não possa voltar pressionando o botão "Voltar"
            finish()
        }
    }
}