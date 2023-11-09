package com.example.sistemalogin

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class form_login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var nome_user: TextView
    private lateinit var text_telaCadastro: TextView
    private lateinit var botoao_login: AppCompatButton
    private lateinit var edit_email: EditText
    private lateinit var edit_senha: EditText
//    private lateinit var db: FirebaseFirestore
    private lateinit var userID: String
    private lateinit var documentReference: DocumentReference

    //private lateinit var progressBar : ProgressBar
    val mensagens = arrayOf(
        "Preencha todos os campos",
        "Cadastro realizado",
        "As senhas não conferem!",
        "A senha deve ter no mínimo 6 caracteres",
        "A senha deve conter pelo menos um número"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_login)
        iniciarComponentes()
        auth = Firebase.auth


        supportActionBar?.hide()
        text_telaCadastro.setOnClickListener {
            onClick()
        }

        botoao_login.setOnClickListener {
            val email: String = edit_email.text.toString()
            val senha: String = edit_senha.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, mensagens[0], Toast.LENGTH_SHORT).show()
            } else {
                signInWithEmailAndPassword(email, senha)
            }
        }
    }

    private fun iniciarComponentes() {
        text_telaCadastro = findViewById(R.id.text_telaCadastro)
        edit_email = findViewById(R.id.edit_email)
        edit_senha = findViewById(R.id.edit_password)
        botoao_login = findViewById(R.id.botao_entrar)
    }

    private fun onClick() {
        val intent = Intent(this, form_cadastro::class.java)
        startActivity(intent)
    }

    private fun signInWithEmailAndPassword(email: String, senha: String) {
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "signInWithEmailAndPassword:success")
                Toast.makeText(this, "Login bem-sucedido", Toast.LENGTH_SHORT).show()
                onClickLogin()
            } else {
                Log.w(TAG, "signInWithEmailAndPassword:failure", task.exception)
                Toast.makeText(this, "Falha na autenticação", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onClickLogin() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        if (auth.currentUser != null) {
            userID = FirebaseAuth.getInstance().currentUser!!.uid
            documentReference = db.collection("usuarios").document(userID)
            documentReference.addSnapshotListener { documentSnapshot, error ->
                runOnUiThread {
                    if (error != null) {
                        Toast.makeText(this, "Falha na autenticação", Toast.LENGTH_SHORT).show()
                    }

                    if (documentSnapshot != null && documentSnapshot.exists()) {
                        // O documento existe, você pode acessar os dados usando documentSnapshot.data
                        val nome = documentSnapshot.getString("nome")
                        if (nome != null) {
                            nome_user = findViewById(R.id.name_user)
                            nome_user.text = nome

                            // Coloque o código para iniciar a nova Activity aqui, após definir o texto em nome_user
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()

                        }
                    }

                    // Finalize a Activity após iniciar a nova Activity e definir o texto em nome_user
                }
            }
        }
    }

}


