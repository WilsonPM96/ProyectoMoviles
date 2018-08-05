package com.example.wilson.proyectomovileswrdv.Detalle_Reservas

import android.os.Parcel
import android.os.Parcelable


class DetalleReservas(var idReserva: Int, var idLugar: Int,  var estado: Int, var fecha: String, var hora_ini: String, var hora_fin: String, var createdAt: Long, var updatedAt: Long) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readLong()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int){
        parcel.writeInt(idReserva)
        parcel.writeInt(idLugar)
        parcel.writeInt(estado)
        parcel.writeString(fecha)
        parcel.writeString(hora_ini)
        parcel.writeString(hora_fin)
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetalleReservas> {
        override fun createFromParcel(parcel: Parcel): DetalleReservas {
            return DetalleReservas(parcel)
        }

        override fun newArray(size: Int): Array<DetalleReservas?> {
            return arrayOfNulls(size)
        }
    }
}