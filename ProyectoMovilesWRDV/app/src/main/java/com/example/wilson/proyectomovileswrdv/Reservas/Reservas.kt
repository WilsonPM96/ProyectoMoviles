package com.example.wilson.proyectomovileswrdv.Reservas

import android.os.Parcel
import android.os.Parcelable

class Reservas(var id: Int, var idUsuario: Int, var idReserva: Int, var fecha_ini: String, var fecha_fin: String, var createdAt: Long, var updatedAt: Long) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readLong()

    )

    
	
	override fun writeToParcel(parcel: Parcel, flags: Int){
        parcel.writeInt(id)
        parcel.writeInt(idUsuario)
        parcel.writeInt(idReserva)
        parcel.writeString(fecha_ini)
        parcel.writeString(fecha_fin)
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

	
    companion object CREATOR : Parcelable.Creator<Reservas> {
        override fun createFromParcel(parcel: Parcel): Reservas {
            return Reservas(parcel)
        }

        override fun newArray(size: Int): Array<Reservas?> {
            return arrayOfNulls(size)
        }
    }
}