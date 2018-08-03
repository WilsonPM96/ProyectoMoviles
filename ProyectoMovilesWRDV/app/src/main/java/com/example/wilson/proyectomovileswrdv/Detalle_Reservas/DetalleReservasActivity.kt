package com.example.wilson.proyectomovileswrdv.Detalle_Reservas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.wilson.proyectomovileswrdv.R
import com.example.wilson.proyectomovileswrdv.Reservas.Reservas
import com.example.wilson.proyectomovileswrdv.Usuario.Usuario
import kotlinx.android.synthetic.main.activity_detalle_reservas.*

class DetalleReservasActivity : AppCompatActivity() {
    var reserva: Reservas? = null
    lateinit var detalle_reserva: ArrayList<DetalleReservas>
    lateinit var adaptador: DetalleReservaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_reservas)
        reserva = intent.getParcelableExtra("detallesReserva")

        txtShowReservaId.text = reserva?.idReserva.toString()

    }
}
