package com.example.wilson.proyectomovileswrdv.Reservas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.wilson.proyectomovileswrdv.Detalle_Reservas.BaseDatosDetalleReservas
import com.example.wilson.proyectomovileswrdv.R
import com.example.wilson.proyectomovileswrdv.Usuario.Usuario
import com.example.wilson.proyectomovileswrdv.Usuario.UsuarioLoggedActivity
import kotlinx.android.synthetic.main.activity_reservas_usuario.*
import android.view.MenuInflater



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

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var reserva = reservas[position]

        when (item.itemId) {

            R.id.item_menu_editar -> {
                val intent = Intent(this, EditarReservaActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("reserva", reserva)
                intent.putExtra("idUsuario", usuario)
                startActivity(intent)
                return true
            }
            R.id.item_menu_eliminar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Esta seguro de eliminar?")
                        .setPositiveButton("Si", { dialog, which ->
                            BaseDatosReservas.eliminarReserva(reserva.id)
                            BaseDatosDetalleReservas.eliminarDetalleReserva(reserva.id)
                            finish()
                            val intent = Intent(this, UsuarioLoggedActivity::class.java)
                            intent.putExtra("idUsuario", usuario)
                            Toast.makeText(this, "Reserva Eliminada con Éxito", Toast.LENGTH_SHORT).show()
                            startActivity(intent)
                        }
                        )
                        .setNegativeButton("No", null)
                val dialogo = builder.create()
                dialogo.show()
                return true
            }
            R.id.item_menu_compartir -> {
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }




}
