package com.example.wilson.proyectomovileswrdv

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.wilson.proyectomovileswrdv.Usuario.RegistrarUsuariosActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_entrar_app.setOnClickListener {
            v: View? -> iraActividadRegistro()
        }

    }

    fun iraActividadRegistro(){
        val intent = Intent(this, RegistrarUsuariosActivity::class.java)
        startActivity(intent)
        finish()
    }



    }


