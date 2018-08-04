package com.example.wilson.proyectomovileswrdv.Lugar

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.wilson.proyectomovileswrdv.Detalle_Reservas.DetalleReservas
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost

class BaseDatosLugar {

    companion object {

        fun insertarLugares(lugares: Array<Lugar?>){
            for (i in lugares.indices) {
                "http://192.168.100.189:1337/Lugares".httpPost(listOf("idLugar" to lugares[i]?.idLugar, "tipoLugar" to lugares[i]?.tipoLugar, "ubicacionLugar" to lugares[i]?.ubicacionLugar, "horarioAtencionLugar" to lugares[i]?.horarioAtencionLugar, "posX" to lugares[i]?.posX, "posY" to lugares[i]?.posY))
                        .responseString { request, _, result ->
                            Log.d("http-ejemplo", request.toString())
                        }
            }
        }

        fun getListofLugares(): ArrayList<Lugar> {
            val lugares: ArrayList<Lugar> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.189:1337/Lugares".httpGet().responseString()
            val jsonStringLugar = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringLugar)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val idLugar = it["idLugar"] as Int
                val tipoLugar = it["tipoLugar"] as String
                val ubicacionLugar = it ["ubicacionLugar"] as String
                val horarioAtencionLugar = it["horarioAtencionLugar"] as String
                val posX = it ["posX"] as Double
                val posY = it["posY"] as Double
                val lugar = Lugar(idLugar, tipoLugar, ubicacionLugar, horarioAtencionLugar, 0, 0, posX, posY)
                lugares.add(lugar)
            }
            return lugares
        }

    }


}