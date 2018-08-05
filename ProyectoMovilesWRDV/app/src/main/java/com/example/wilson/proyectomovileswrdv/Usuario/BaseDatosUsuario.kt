package com.example.wilson.proyectomovileswrdv.Usuario

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost


class BaseDatosUsuario {
    companion object {

        fun insertarUsuario(usuario: Usuario) {
            "http://192.168.100.189:1337/Usuarios".httpPost(listOf("idUsuario" to usuario.idUsuario,"username" to usuario.username, "password" to usuario.password))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getList(): ArrayList<Usuario> {
            val usuarios: ArrayList<Usuario> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.189:1337/Usuarios".httpGet().responseString()
            val jsonStringUsuario = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringUsuario)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val idUsuario = it["idUsuario"] as Int
                val username = it["username"] as String
                val password = it["password"] as String
                val usuario = Usuario(idUsuario, username, password, 0, 0)
                usuarios.add(usuario)
            }
            return usuarios
        }

    }
}

