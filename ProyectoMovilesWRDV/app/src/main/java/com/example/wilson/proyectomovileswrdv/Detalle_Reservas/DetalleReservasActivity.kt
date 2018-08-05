package com.example.wilson.proyectomovileswrdv.Detalle_Reservas

import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.example.wilson.proyectomovileswrdv.R
import com.example.wilson.proyectomovileswrdv.Reservas.BaseDatosReservas
import com.example.wilson.proyectomovileswrdv.Reservas.EditarReservaActivity
import com.example.wilson.proyectomovileswrdv.Reservas.Reservas
import com.example.wilson.proyectomovileswrdv.Usuario.UsuarioLoggedActivity
import kotlinx.android.synthetic.main.activity_detalle_reservas.*
import kotlinx.android.synthetic.main.activity_reservas_usuario.*

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

        registerForContextMenu(recycler_view_detalle_reservas)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var detalleReserva = detalle_reservas[position]

        when (item.itemId) {

            R.id.item_menu_compartir_telefono-> {

                val intent = Intent(this, IntentTelefonoDetalleReservaActivity::class.java)
                intent.putExtra("detalleReserva", detalleReserva)
                intent.putExtra("idReserva", reservas)
                startActivity(intent)
                finish()
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }




}
