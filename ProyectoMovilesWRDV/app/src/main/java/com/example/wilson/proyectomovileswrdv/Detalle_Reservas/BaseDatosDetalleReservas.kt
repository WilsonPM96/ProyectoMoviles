package com.example.wilson.proyectomovileswrdv.Detalle_Reservas

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.wilson.proyectomovileswrdv.Reservas.Reservas
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost

class BaseDatosDetalleReservas {
    companion object {


   /* fun insertarDetalleReserva(detalleReserva: DetalleReservas) {
        "http://172.29.64.146:1337/DetalleReserva".httpPost(listOf("idReserva" to reserva.idReserva,"fecha_ini" to reserva.fecha_ini, "fecha_fin" to reserva.fecha_fin, "idUsuario" to reserva.idUsuario))
                .responseString { request, _, result ->
                    Log.d("http-ejemplo", request.toString())
                }
    }*/

    fun getListofDetallesReservas(idReserva: Int): ArrayList<DetalleReservas> {
        val detalleReservas: ArrayList<DetalleReservas> = ArrayList()
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val (request, response, result) = "http://192.168.100.189:1337/DetalleReserva?idReserva=$idReserva".httpGet().responseString()
        val jsonStringDetalleReservas = result.get()

        val parser = Parser()
        val stringBuilder = StringBuilder(jsonStringDetalleReservas)
        val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

        array.forEach {
            val idReserva = it["idReserva"] as Int
            val idLugar = it["idLugar"] as Int
            val estado = it["estado"] as Int
            val fecha = it ["fecha"] as String
            val hora_ini = it["hora_ini"] as String
            val hora_fin = it ["hora_fin"] as String
            val detallereserva = DetalleReservas(idReserva, idLugar, fecha, estado, hora_ini, hora_fin,  0, 0)
            detalleReservas.add(detallereserva)
        }
        return detalleReservas
    }

}
}