package com.example.wilson.proyectomovileswrdv.Reservas

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.wilson.proyectomovileswrdv.Usuario.Usuario
import com.github.kittinunf.fuel.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class BaseDatosReservas {
    companion object {

        fun insertarReserva(reserva: Reservas) {
            "http://192.168.100.189:1337/Reservas".httpPost(listOf("idUsuario" to reserva.idUsuario, "idReserva" to reserva.idReserva,"fecha_ini" to reserva.fecha_ini, "fecha_fin" to reserva.fecha_fin))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun eliminarReserva(id: Int) {
            "http://192.168.100.189:1337/Reservas/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun actualizarReserva(reserva: Reservas) {
            eliminarReserva(reserva.id)
            "http://192.168.100.189:1337/Reservas".httpPost(listOf("idUsuario" to reserva.idUsuario, "idReserva" to reserva.idReserva,"fecha_ini" to reserva.fecha_ini, "fecha_fin" to reserva.fecha_fin))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }


        }

        fun getListofReservas(idUsuario: Int): ArrayList<Reservas> {
            val reservas: ArrayList<Reservas> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.189:1337/Reservas?idUsuario=$idUsuario".httpGet().responseString()
            val jsonStringReservas = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringReservas)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it ["id"] as Int
                val idReserva = it["idReserva"] as Int
                val fecha_ini = it["fecha_ini"] as String
                val fecha_fin = it["fecha_fin"] as String
                val idUsuario = it ["idUsuario"] as Int
                val reserva = Reservas(id, idUsuario, idReserva, fecha_ini, fecha_fin,  0, 0)
                reservas.add(reserva)
            }
            return reservas
        }

    }
}