  package com.example.sistemalogin


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

import com.example.sistemalogin.databinding.ActivityMainBinding


  class MainActivity : AppCompatActivity() {
      private lateinit var biding: ActivityMainBinding
      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          biding = ActivityMainBinding.inflate(layoutInflater)
          setContentView(biding.root)
          replaceFragment(home())
          biding.bottomNavigationView.setOnItemSelectedListener {
              when(it.itemId){
                  R.id.botao_home -> replaceFragment(home())
                  R.id.botao_menu -> replaceFragment(menu())
                  R.id.botao_ligas -> replaceFragment(ligas())
                  R.id.botao_favoritos -> replaceFragment(favorites())
                  else ->{

                  }
              }
              true
          }


      }


      private fun replaceFragment(fragment : Fragment){
          val fragmentManager = supportFragmentManager
          val fragmentTransaction = fragmentManager.beginTransaction()
          fragmentTransaction.replace(R.id.frameLayout_main,fragment)
          fragmentTransaction.commit()
      }





      }


