package com.example.wilson.proyectomovileswrdv.Lugar

import android.os.Parcel
import android.os.Parcelable

class Lugar(var idLugar: Int, var tipoLugar: String, var ubicacionLugar: String, var horarioAtencionLugar: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int)  {
        parcel.writeInt(idLugar)
        parcel.writeString(tipoLugar)
        parcel.writeString(ubicacionLugar)
        parcel.writeString(horarioAtencionLugar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Lugar> {
        override fun createFromParcel(parcel: Parcel): Lugar {
            return Lugar(parcel)
        }

        override fun newArray(size: Int): Array<Lugar?> {
            return arrayOfNulls(size)
        }
    }
}