package com.example.wilson.proyectomovileswrdv.Usuario

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.wilson.proyectomovileswrdv.GoogleMapsActivity
import com.example.wilson.proyectomovileswrdv.Lugar.BaseDatosLugar
import com.example.wilson.proyectomovileswrdv.MainActivity
import com.example.wilson.proyectomovileswrdv.R
import com.example.wilson.proyectomovileswrdv.Reservas.ReservasUsuarioActivity
import kotlinx.android.synthetic.main.activity_usuario_logged.*

class UsuarioLoggedActivity : AppCompatActivity() {
    var usuario: Usuario? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario_logged)
        usuario= intent.getParcelableExtra("idUsuario")
        btn_reservar.setOnClickListener{
            v: View? -> iraReservar()
        }

        btn_reservas.setOnClickListener {
            v: View? -> iraReservasHechas()
        }

        btn_salir.setOnClickListener{
            v: View? -> iraPantallaPrincipal()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_menu_reservar -> {
                Toast.makeText(this, "Reservar", Toast.LENGTH_SHORT).show()
                iraReservar()
                return true
            }
            R.id.item_menu_reservas -> {
                Toast.makeText(this, "Reservas Hechas", Toast.LENGTH_SHORT).show()
                iraReservasHechas()
                return true
            }
            R.id.item_menu_salir -> {
                iraPantallaPrincipal()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun iraReservar(){
        val intent= Intent(this, GoogleMapsActivity::class.java)
        intent.putExtra("idUsuario", usuario)
        startActivity(intent)
    }

    fun iraReservasHechas(){
        val intent= Intent(this, ReservasUsuarioActivity::class.java)
        intent.putExtra("idUsuario", usuario)
        startActivity(intent)

    }

    fun iraPantallaPrincipal(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Te esperamos de regreso", Toast.LENGTH_SHORT).show()
        finish()
    }
}
