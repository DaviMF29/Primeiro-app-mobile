package com.example.sistemalogin

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap
import java.util.Objects

class form_cadastro : AppCompatActivity() {
    private lateinit var edit_nome: EditText
    private lateinit var edit_email: EditText
    private lateinit var edit_senha: EditText
    private lateinit var edit_confirmSenha: EditText
    private lateinit var botaoCadastro : Button
    private lateinit var snackbar : Snackbar
    private lateinit var auth: FirebaseAuth

    val mensagens = arrayOf("Preencha todos os campos", "Cadastro realizado", "As senhas não conferem!","A senha deve ter no mínimo 6 caracteres"
    ,"A senha deve conter pelo menos um número")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_cadastro)
        iniciarComponentes()
        // Initialize Firebase Auth
        auth = Firebase.auth


        supportActionBar?.hide()


        botaoCadastro.setOnClickListener{
            val nome_user : String = edit_nome.text.toString()
            val email_user : String = edit_email.text.toString()
            val senha_user : String = edit_senha.text.toString()
            val confirmSenha_user : String = edit_confirmSenha.text.toString()
            val containsNumbers = !senha_user.matches(Regex("[0-9]"))

            if(nome_user.isEmpty() || email_user.isEmpty() || senha_user.isEmpty() || confirmSenha_user.isEmpty()) run {
                snackbar = Snackbar.make(it, mensagens[0], Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.WHITE)
                snackbar.setTextColor(Color.DKGRAY)
                snackbar.show()
            }
            else if(senha_user != confirmSenha_user){
                snackbar = Snackbar.make(it, mensagens[2], Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.WHITE)
                snackbar.setTextColor(Color.DKGRAY)
                snackbar.show()
            }
            else if(senha_user.length <6){
                snackbar = Snackbar.make(it, mensagens[3], Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.WHITE)
                snackbar.setTextColor(Color.DKGRAY)
                snackbar.show()
            }
            else if(!containsNumbers){
                snackbar = Snackbar.make(it, mensagens[4], Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.WHITE)
                snackbar.setTextColor(Color.DKGRAY)
                snackbar.show()
            }

            else{
                    onClick()
                    cadastarUser()
                    salvarUsuario()

            }


        }
    }

    private fun cadastarUser(){
        val email_user : String = edit_email.text.toString()
        val senha_user : String = edit_senha.text.toString()

        auth.createUserWithEmailAndPassword(email_user,senha_user).addOnCompleteListener(this){
            task -> if(task.isSuccessful){
            Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
        }
            else{
            Toast.makeText(this, "Erro no cadastro do usuário", Toast.LENGTH_SHORT).show()
        }
        }
    }
    private fun iniciarComponentes() {
        edit_nome = findViewById(R.id.cadastrar_nome)
        edit_email = findViewById(R.id.cadastar_email)
        edit_senha = findViewById(R.id.cadastrar_senha)
        edit_confirmSenha = findViewById(R.id.confirmacao_senha)
        botaoCadastro = findViewById(R.id.botao_cadastrar)
    }

    private fun onClick() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun salvarUsuario() {
        val nome: String = edit_nome.text.toString()
        val email: String = edit_email.text.toString()

        // Crie um HashMap para armazenar os dados do usuário
        val usuario: MutableMap<String, Any> = HashMap()
        usuario["nome"] = nome
        usuario["email"] = email
        // Adicione outros campos do usuário, se necessário
        // usuario["campo"] = valor

        // Obtenha uma referência para o banco de dados Firestore
        val db = FirebaseFirestore.getInstance()


        // Adicione os dados do usuário ao Firestore
        db.collection("usuarios")
            .add(usuario)
            .addOnSuccessListener { documentReference ->
                // Sucesso ao adicionar dados ao Firestore
                Log.d(TAG, "Documento adicionado com ID: ${documentReference.id}")
                Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                // Falha ao adicionar dados ao Firestore
                Log.w(TAG, "Erro ao adicionar documento", e)
                Toast.makeText(this, "Erro ao salvar dados.", Toast.LENGTH_SHORT).show()
                print("erro")
            }
    }

}


