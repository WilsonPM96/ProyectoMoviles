package com.example.wilson.proyectomovileswrdv.Detalle_Reservas

import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import com.example.wilson.proyectomovileswrdv.R

class DetalleReservaAdapter(private val detallereservasList: List<DetalleReservas>) :  RecyclerView.Adapter<DetalleReservaAdapter.MyViewHolder>() {
    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var idLugar: TextView
        var fecha: TextView
        var hora_ini: TextView
        var hora_fin: TextView

        lateinit var detallereserva: DetalleReservas

        init {
            idLugar = view.findViewById(R.id.txtShowIdLugar) as TextView
            fecha = view.findViewById(R.id.txtShowFecha) as TextView
            hora_ini = view.findViewById(R.id.txtShowHoraIni) as TextView
            hora_fin = view.findViewById(R.id.txtShowHoraFin) as TextView
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            menu?.add(Menu.NONE, R.id.item_menu_editar, Menu.NONE, "Editar")
            menu?.add(Menu.NONE, R.id.item_menu_eliminar, Menu.NONE, "Eliminar")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetalleReservaAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_detallereservas_layout, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val detallereserva = detallereservasList[position]
        holder.idLugar.text = detallereserva.idLugar.toString()
        holder.fecha.text = detallereserva.fecha
        holder.hora_ini.text = detallereserva.hora_ini
        holder.hora_fin.text = detallereserva.hora_fin
        holder.detallereserva = detallereserva

        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return detallereservasList.size
    }
}