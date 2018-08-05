package com.example.wilson.proyectomovileswrdv.Detalle_Reservas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import com.example.wilson.proyectomovileswrdv.R
import com.example.wilson.proyectomovileswrdv.Reservas.Reservas
import kotlinx.android.synthetic.main.activity_detalle_reservas.*

class DetalleReservasActivity : AppCompatActivity() {
    var reservas: Reservas? = null
    lateinit var detalle_reservas: ArrayList<DetalleReservas>
    lateinit var adaptador: DetalleReservaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_reservas)
        reservas = intent.getParcelableExtra("detallesReserva")
        txtShowUsuarioId.text = reservas?.idUsuario.toString()
        txtShowReservaId.text = reservas?.idReserva.toString()
        txtShowFechaIni.text = reservas?.fecha_ini
        txtShowFechaFin.text = reservas?.fecha_fin

        detalle_reservas = BaseDatosDetalleReservas.getListofDetallesReservas(reservas?.idReserva!!)
        val layoutManager = LinearLayoutManager(this)
        adaptador = DetalleReservaAdapter(detalle_reservas)
        recycler_view_detalle_reservas.layoutManager = layoutManager
        recycler_view_detalle_reservas.itemAnimator = DefaultItemAnimator()
        recycler_view_detalle_reservas.adapter = adaptador
        adaptador.notifyDataSetChanged()



    }

}
