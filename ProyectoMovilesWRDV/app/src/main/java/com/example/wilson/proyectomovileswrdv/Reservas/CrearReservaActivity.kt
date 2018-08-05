package com.example.wilson.proyectomovileswrdv.Reservas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.wilson.proyectomovileswrdv.Detalle_Reservas.BaseDatosDetalleReservas
import com.example.wilson.proyectomovileswrdv.Detalle_Reservas.DetalleReservas
import com.example.wilson.proyectomovileswrdv.Lugar.Lugar
import com.example.wilson.proyectomovileswrdv.R
import com.example.wilson.proyectomovileswrdv.Usuario.Usuario
import com.example.wilson.proyectomovileswrdv.Usuario.UsuarioLoggedActivity
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_crear_reserva.*
import java.util.*

class CrearReservaActivity : AppCompatActivity()  {

    lateinit var detalle_reservas: ArrayList<DetalleReservas>
    var lugar: Lugar? = null
    var usuario: Usuario? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_reserva)
        lugar = intent.getParcelableExtra("idLugar")
        usuario = intent.getParcelableExtra("idUsuario")

        editTextRidLugar.text = lugar?.idLugar.toString()
        editTextRidUsuario.text = usuario?.idUsuario.toString()
        btn_hacerReserva.setOnClickListener {
            v: View? -> registrarDetalleReserva()
        }


    }

    fun registrarDetalleReserva(){
        val id = 0
        val random = Random()
        val idLugar = editTextRidLugar.text.toString().toInt()
        val horaIni= editTextHoraIni.text.toString()
        val horaFin = editTextHoraFin.text.toString()
        val fecha = editTextFechaIni.text.toString()
        val estado = if (switchEstadoReserva.isChecked) 1 else 0

        var detalle_reserva = DetalleReservas(id, random.nextInt(1..100), idLugar, estado, fecha , horaIni, horaFin, 0, 0)

        detalle_reservas= BaseDatosDetalleReservas.getListofDetallesReservasTotales()
        BaseDatosDetalleReservas.insertarDetalleReserva(detalle_reserva)
        Toast.makeText(this,"Detalle de Reserva Registrado con Éxito", Toast.LENGTH_SHORT).show()
        llenarReservaInfo(detalle_reserva.idReserva, detalle_reserva.hora_ini)

    }

    fun llenarReservaInfo(idReserva: Int, hora_ini: String){
        val id = 0
        val idReserva = idReserva
        val idUsuario = editTextRidUsuario.text.toString().toInt()
        val fechaIni = editTextFechaIni.text.toString()
        val fechaFin = editTextFechaFin.text.toString()

        var reserva = Reservas(id, idUsuario,idReserva, fechaIni, fechaFin, 0,0 )

        BaseDatosReservas.insertarReserva(reserva)
        Alerter.create(this)
                .setTitle("Reservación Realizada con Éxito")
                .setText("Recuerde su reservación a las $hora_ini horas")
                .setDuration(8000)
                .enableSwipeToDismiss()
                .setOnClickListener({
                    irUsuarioLogged()
                })
                .show()


    }

    fun irUsuarioLogged(){
        val intent = Intent(this, UsuarioLoggedActivity::class.java)
        intent.putExtra("idUsuario", usuario)
        startActivity(intent)
        finish()
    }

    fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
    }


}
