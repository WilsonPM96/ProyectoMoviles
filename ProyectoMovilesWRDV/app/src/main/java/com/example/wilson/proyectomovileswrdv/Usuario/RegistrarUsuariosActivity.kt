package com.example.wilson.proyectomovileswrdv.Usuario

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.wilson.proyectomovileswrdv.R
import kotlinx.android.synthetic.main.activity_registrar_usuarios.*

import java.util.*

class RegistrarUsuariosActivity : AppCompatActivity() {

    lateinit var usuarios: ArrayList<Usuario>
    var estadoIngresoSistema = 0
    lateinit var usuarioActual:String
    var usuarioActualId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuarios)
        button_registrar.setOnClickListener{
            view: View? ->  registrarUsuario()
        }
        btn_ingresar.setOnClickListener {
            view: View? -> loguearse()
        }


    }


    private fun registrarUsuario() {
        val random = Random()
        val username= editText_nombre.text.toString()
        val passwordUsuario= editText_pass.text.toString()

        var usuario = Usuario(random.nextInt(1..100), username, passwordUsuario, 0, 0)

        usuarios= BaseDatosUsuario.getList()

        for(datos in usuarios){
            if(datos.idUsuario == usuario.idUsuario || datos.username.equals(username, true)){
                Toast.makeText(this,"Id de Usuario o nombre ya existen", Toast.LENGTH_SHORT).show()
            }
            else {
                BaseDatosUsuario.insertarUsuario(usuario)
                Toast.makeText(this,"Usuario Registrado con Exito", Toast.LENGTH_SHORT).show()
                usuarioActual = datos.username
                val intent = Intent(this, UsuarioLoggedActivity::class.java)
                startActivity(intent)

            }
        }


    }

    private fun loguearse(){

            val usernameUsuario = editText_nombre.text.toString()
            val passwordUsuario = editText_pass.text.toString()


            usuarios = BaseDatosUsuario.getList()

            for (datos in usuarios){

                if (datos.username.equals(usernameUsuario,true) && datos.password.equals(passwordUsuario,true)){
                    estadoIngresoSistema = 1
                    usuarioActual = datos.username
                    usuarioActualId = datos.idUsuario
                }

            }


            if (estadoIngresoSistema==1){
                Toast.makeText(this,"Bienvenido: $usuarioActual", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, UsuarioLoggedActivity::class.java)
                intent.putExtra("idUsuario",usuarioActual)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Datos o Usuario Incorrectos", Toast.LENGTH_SHORT).show()
            }


    }

    fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
    }

    }
