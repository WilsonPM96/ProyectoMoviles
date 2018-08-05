package com.example.wilson.proyectomovileswrdv.Reservas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.wilson.proyectomovileswrdv.Detalle_Reservas.BaseDatosDetalleReservas
import com.example.wilson.proyectomovileswrdv.Detalle_Reservas.DetalleReservas
import com.example.wilson.proyectomovileswrdv.R
import com.example.wilson.proyectomovileswrdv.Usuario.Usuario
import com.example.wilson.proyectomovileswrdv.Usuario.UsuarioLoggedActivity
import kotlinx.android.synthetic.main.activity_crear_reserva.*
import kotlinx.android.synthetic.main.activity_editar_reserva.*

class EditarReservaActivity : AppCompatActivity() {
    var usuario: Usuario? = null
    var reserva: Reservas? = null
    lateinit var detalle_reservas: ArrayList<DetalleReservas>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_reserva)

        val tipoAccion = intent.getStringExtra("tipo")

        if (tipoAccion.equals("Edit")) {
            usuario = intent.getParcelableExtra("idUsuario")
            reserva = intent.getParcelableExtra("reserva")
            detalle_reservas = BaseDatosDetalleReservas.getListofDetallesReservas(reserva?.idReserva!!)
            llenarCampos()
        }
        btn_guardarCambios.setOnClickListener {
            v: View? -> guardarCambios()
        }
    }

    fun llenarCampos() {
        editTextERidLugar.setText(detalle_reservas[0].idLugar.toString())
        editTextERidUsuario.setText(reserva?.idUsuario.toString())
        editTextEFechaIni.setText(reserva?.fecha_ini)
        editTextEFechaFin.setText(reserva?.fecha_fin)
        editTextEHoraIni.setText(detalle_reservas[0].hora_ini)
        editTextEHoraFin.setText(detalle_reservas[0].hora_fin)
    }

    fun guardarCambios(){
        val id = detalle_reservas[0].id.toString().toInt()
        val idReserva = detalle_reservas[0].idReserva.toString().toInt()
        val idLugar = editTextERidLugar.text.toString().toInt()
        val horaIni= editTextEHoraIni.text.toString()
        val horaFin = editTextEHoraFin.text.toString()
        val fecha = editTextEFechaIni.text.toString()
        val estado = if (switchEEstadoReserva.isChecked) 1 else 0

        var detalle_reserva = DetalleReservas(id, idReserva, idLugar, estado, fecha , horaIni, horaFin, 0, 0)

        BaseDatosDetalleReservas.actualizarDetalleReserva(detalle_reserva)
        Toast.makeText(this,"Detalle de Reserva Actualizado con Éxito", Toast.LENGTH_SHORT).show()
        guardarReservaInfo(detalle_reserva.idReserva, detalle_reserva.id)


        }


    fun guardarReservaInfo(idReserva: Int, id: Int){
        val id = id
        val idReserva = idReserva
        val idUsuario = editTextERidUsuario.text.toString().toInt()
        val fechaIni = editTextEFechaIni.text.toString()
        val fechaFin = editTextEFechaFin.text.toString()

        var reserva = Reservas(id, idUsuario,idReserva, fechaIni, fechaFin, 0,0 )

        BaseDatosReservas.actualizarReserva(reserva)
        val intent = Intent(this, ReservasUsuarioActivity::class.java)
        intent.putExtra("idUsuario", usuario)
        startActivity(intent)
        finish()
    }

}
