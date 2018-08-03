package com.example.wilson.proyectomovileswrdv.Detalle_Reservas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.example.wilson.proyectomovileswrdv.R
import com.example.wilson.proyectomovileswrdv.Reservas.Reservas
import com.example.wilson.proyectomovileswrdv.Reservas.ReservasUsuarioAdapter
import com.example.wilson.proyectomovileswrdv.Usuario.Usuario
import kotlinx.android.synthetic.main.activity_detalle_reservas.*
import kotlinx.android.synthetic.main.activity_reservas_usuario.*

class DetalleReservasActivity : AppCompatActivity() {
    var detalle_reserva: DetalleReservas? = null
    lateinit var detalle_reservas: ArrayList<DetalleReservas>
    lateinit var adaptador: DetalleReservaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_reservas)
        detalle_reserva = intent.getParcelableExtra("idReserva")

        detalle_reservas = BaseDatosDetalleReservas.getListofDetallesReservas()
        txtShowIdLugar.text = detalle_reservas[0].idLugar.toString()
        txtShowReservaId.text = detalle_reservas[0].idReserva.toString()
        txtShowfecha.text = detalle_reservas[0].fecha
        txtShowhoraIni.text = detalle_reservas[0].hora_ini
        txtShowhoraFin.text = detalle_reservas[0].hora_fin
        val layoutManager = LinearLayoutManager(this)
        adaptador = DetalleReservaAdapter(detalle_reservas)
        recycler_view_detalle_reservas.layoutManager = layoutManager
        recycler_view_detalle_reservas.itemAnimator = DefaultItemAnimator()
        recycler_view_detalle_reservas.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_detalle_reservas)


    }
}
