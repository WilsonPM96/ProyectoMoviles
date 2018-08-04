package com.example.wilson.proyectomovileswrdv.Reservas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.example.wilson.proyectomovileswrdv.R
import com.example.wilson.proyectomovileswrdv.Usuario.Usuario
import kotlinx.android.synthetic.main.activity_reservas_usuario.*

class ReservasUsuarioActivity : AppCompatActivity() {
    var usuario: Usuario? = null
    lateinit var reservas: ArrayList<Reservas>
    lateinit var adaptador: ReservasUsuarioAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas_usuario)
        usuario = intent.getParcelableExtra("idUsuario")
        reservas = BaseDatosReservas.getListofReservas(usuario?.idUsuario!!)

        val layoutManager = LinearLayoutManager(this)
        adaptador = ReservasUsuarioAdapter(reservas)
        recyclerview_Reservas.layoutManager = layoutManager
        recyclerview_Reservas.itemAnimator = DefaultItemAnimator()
        recyclerview_Reservas.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recyclerview_Reservas)
    }
}
