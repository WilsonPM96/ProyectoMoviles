package com.example.wilson.proyectomovileswrdv.Detalle_Reservas

import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import com.example.wilson.proyectomovileswrdv.R
import com.example.wilson.proyectomovileswrdv.Reservas.Reservas
import com.example.wilson.proyectomovileswrdv.Reservas.ReservasUsuarioActivity
import com.example.wilson.proyectomovileswrdv.Usuario.Usuario
import com.example.wilson.proyectomovileswrdv.Usuario.UsuarioLoggedActivity
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_intent_telefono_detalle_reserva.*

class IntentTelefonoDetalleReservaActivity : AppCompatActivity() {
    var detalle_Reserva: DetalleReservas? = null
    var reserva: Reservas? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_telefono_detalle_reserva)
        detalle_Reserva = intent.getParcelableExtra("detalleReserva")
        reserva = intent.getParcelableExtra("idReserva")
        editTextReservaIdt.text = detalle_Reserva?.idReserva.toString()
        editTextHoraIniT.text = detalle_Reserva?.hora_ini
        editTextHoraFinT.text = detalle_Reserva?.hora_fin
        seleccionarUnContacto()

        btn_enviarInvitacionTelefono.setOnClickListener {
            v: View? -> enviarInvitacion()
        }
    }

    fun seleccionarUnContacto() {
        val intent = Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
        startActivityForResult(intent, RESPUESTA_DE_CONTACTO)
    }

    companion object {
        val RESPUESTA_DE_CONTACTO = 1;
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        Log.i("etiqueta","El request code es: ${requestCode}")

        when (requestCode) {
            IntentTelefonoDetalleReservaActivity.RESPUESTA_DE_CONTACTO -> {
                if (resultCode == RESULT_OK) {
                    var cursor: Cursor? = null
                    try {
                        var phoneNo: String? = null
                        var name: String? = null
                        val uri = intent?.data
                        cursor = contentResolver.query(uri, null, null, null, null)
                        cursor!!.moveToFirst()
                        val phoneIndex = cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        val nameIndex = cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                        phoneNo = cursor!!.getString(phoneIndex)
                        name = cursor!!.getString(nameIndex)


                        editTextNombreContacto.text = name
                        editTextNumeroContacto.text = phoneNo

                        Log.i("create", "Nombre ${name}")
                        Log.i("create", "Numero de telefono ${phoneNo}")


                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                if (resultCode == RESULT_CANCELED) {
                    Log.i("create", "El usuario cancelo")
                }
            }
        }
    }

    fun enviarInvitacion(){
        Alerter.create(this)
                .setTitle("Invitación enviada con Éxito")
                .setText("Se ha invitado a ${editTextNombreContacto.text} al evento")
                .setDuration(8000)
                .enableSwipeToDismiss()
                .setOnClickListener({
                    irDetalleReservas()
                })
                .show()


    }

    fun irDetalleReservas(){
        val intent = Intent(this, DetalleReservasActivity::class.java)
        intent.putExtra("detallesReserva", reserva)
        startActivity(intent)
        finish()
    }
}
