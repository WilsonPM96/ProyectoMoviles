package com.example.wilson.proyectomovileswrdv.Reservas

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView
import com.example.wilson.proyectomovileswrdv.Detalle_Reservas.DetalleReservasActivity
import com.example.wilson.proyectomovileswrdv.R

class ReservasUsuarioAdapter (private val reservasList: List<Reservas>) :  RecyclerView.Adapter<ReservasUsuarioAdapter.MyViewHolder>(){

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var idUser: TextView
        var fecha_ini : TextView
        var fecha_fin: TextView
        var detalles: Button

        lateinit var reserva: Reservas

        init {
            idUser = view.findViewById(R.id.txtUsername) as TextView
            fecha_ini = view.findViewById(R.id.txtFecha_Ini) as TextView
            fecha_fin = view.findViewById(R.id.txtFecha_Fin) as TextView
            detalles = view.findViewById(R.id.btnDetallesReservas) as Button
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            menu?.add(Menu.NONE, R.id.item_menu_editar, Menu.NONE, "Editar")
            menu?.add(Menu.NONE, R.id.item_menu_eliminar, Menu.NONE, "Eliminar")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_reservas_layout, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val reserva = reservasList[position]
        holder.idUser.text = reserva.idUsuario.toString()
        holder.fecha_ini.text = reserva.fecha_ini
        holder.fecha_fin.text = reserva.fecha_fin
        holder.reserva = reserva
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetalleReservasActivity::class.java)
            intent.putExtra("detallesReserva",reserva)
            v.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return reservasList.size
    }


}