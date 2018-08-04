package com.example.wilson.proyectomovileswrdv
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.wilson.proyectomovileswrdv.Lugar.BaseDatosLugar
import com.example.wilson.proyectomovileswrdv.Lugar.Lugar
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_google_maps.*
import java.util.*
import kotlin.collections.ArrayList

class GoogleMapsActivity :
        AppCompatActivity(),
        OnMapReadyCallback,
        GoogleMap.OnCameraMoveStartedListener,
        GoogleMap.OnCameraMoveListener,
        GoogleMap.OnCameraMoveCanceledListener,
        GoogleMap.OnCameraIdleListener{


    private lateinit var mMap: GoogleMap



    var arregloLugares : ArrayList<Lugar> = BaseDatosLugar.getListofLugares()
    var arregloMarcadores = ArrayList<Marker>()



    val epnLatLang = LatLng(arregloLugares[1].posX, arregloLugares[1].posY)
    val centralLatLang= LatLng(arregloLugares[8].posX, arregloLugares[8].posY)
    val uteLatLang= LatLng(arregloLugares[2].posX, arregloLugares[2].posY)
    val udlaLatLang= LatLng(arregloLugares[3].posX, arregloLugares[3].posY)
    val usfqLatLang= LatLng(arregloLugares[4].posX, arregloLugares[4].posY)

    val multicinesRecreo = LatLng(arregloLugares[5].posX, arregloLugares[5].posY)
    val multicinesCCI= LatLng(arregloLugares[6].posX, arregloLugares[6].posY)
    val multicinesQS= LatLng(arregloLugares[7].posX, arregloLugares[7].posY)
    val supercinesQS= LatLng(arregloLugares[0].posX, arregloLugares[0].posY)
    val supercines6D= LatLng(arregloLugares[9].posX, arregloLugares[9].posY)
    val cineMark= LatLng(arregloLugares[10].posX, arregloLugares[10].posY)

    val parqueMetropolitano= LatLng(arregloLugares[11].posX, arregloLugares[11].posY)
    val parquelasCuadras= LatLng(arregloLugares[12].posX, arregloLugares[12].posY)
    val parqueEjido= LatLng(arregloLugares[13].posX, arregloLugares[13].posY)
    val parqueCarolina= LatLng(arregloLugares[14].posX, arregloLugares[14].posY)
    val parqueBicentenario= LatLng(arregloLugares[15].posX, arregloLugares[15].posY)


    val zoom = 17f

    val clienteLatLang = LatLng(-0.260784, -78.538662)
    val cliente: CameraPosition = CameraPosition
            .Builder()
            .target(clienteLatLang)
            .zoom(zoom)
            .build()


    var usuarioTienePermisosLocalizacion = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_maps)

        solicitarPermisosLocalizacion()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        with(googleMap) {

            establecerListeners(googleMap)
            establecerSettings(googleMap)


            anadirMarcador(epnLatLang, "Escuela Politécnica Nacional")
            anadirMarcador(centralLatLang, "Universidad Central")
            anadirMarcador(uteLatLang, "Universidad Tecnológica Equinoccial")
            anadirMarcador(udlaLatLang, "Universidad de las Américas")
            anadirMarcador(usfqLatLang, "Universidad San Francisco de Quito")
            anadirMarcador(multicinesRecreo, "Multicines Recreo")
            anadirMarcador(multicinesCCI, "Multicines CCI")
            anadirMarcador(multicinesQS, "Multicines Quicentro Shopping")
            anadirMarcador(supercinesQS, "Supercines Quicentro Sur")
            anadirMarcador(supercines6D, "Supercines 6 Diciembre")
            anadirMarcador(cineMark, "Cinemark")
            anadirMarcador(parqueMetropolitano, "Parque Metropolitano")
            anadirMarcador(parquelasCuadras, "Parque las Cuadras")
            anadirMarcador(parqueEjido, "Parque Ejido")
            anadirMarcador(parqueCarolina, "Parque Carolina")
            anadirMarcador(parqueBicentenario, "Parque Bicentenario")


            moverCamaraPorLatLongZoom(epnLatLang, zoom)
            btn_buscar_por_voz.setOnClickListener {
                v:  View? -> getSpeechInput(v)
            }

        }
    }

    fun solicitarPermisosLocalizacion() {
        if (ContextCompat.checkSelfPermission(this.applicationContext,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            usuarioTienePermisosLocalizacion = true
        } else {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }

    private fun establecerSettings(googleMap: GoogleMap) {
        with(googleMap) {
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
            uiSettings.isZoomGesturesEnabled = true
            uiSettings.isCompassEnabled = true
        }
    }

    private fun anadirMarcador(latitudLongitud: LatLng, titulo: String) {


        arregloMarcadores = ArrayList<Marker>()

        val marker = mMap.addMarker(
                MarkerOptions()
                        .position(latitudLongitud)
                        .title(titulo)
        )

        arregloMarcadores.add(marker)

        Log.i("google-map", "$arregloMarcadores")
    }


    private fun moverCamaraPorLatLongZoom(latitudLongitud: LatLng, zoom: Float) {


        mMap.moveCamera(
                CameraUpdateFactory
                        .newLatLngZoom(latitudLongitud, zoom)
        )


    }


    private fun moverCamaraPorPosicion(posicionCamara: CameraPosition) {
        mMap.moveCamera(
                CameraUpdateFactory
                        .newCameraPosition(posicionCamara)
        )
    }


    private fun establecerListeners(googleMap: GoogleMap) {
        with(googleMap) {

            setOnCameraIdleListener(this@GoogleMapsActivity)
            setOnCameraMoveStartedListener(this@GoogleMapsActivity)
            setOnCameraMoveListener(this@GoogleMapsActivity)
            setOnCameraMoveCanceledListener(this@GoogleMapsActivity)
        }
    }



    override fun onCameraMoveStarted(p0: Int) {
    }

    override fun onCameraMove() {
    }

    override fun onCameraMoveCanceled() {
    }

    override fun onCameraIdle() {
    }

    fun getSpeechInput (view: View?){
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivityForResult(intent,10)
        }
        else{
            Toast.makeText(this,"\n" +
                    "Su dispositivo no es compatible con la entrada de voz", Toast.LENGTH_SHORT ).show()
        }
    }

    override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent){
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            10 -> if (resultCode == Activity.RESULT_OK){
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                txtBuscarLugar.setText(result.get(0))

                when{
                    txtBuscarLugar.text.contains("escuela politecnica nacional") -> moverCamaraPorLatLongZoom(epnLatLang, zoom)
                    txtBuscarLugar.text.contains("epn") -> moverCamaraPorLatLongZoom(epnLatLang, zoom)
                    txtBuscarLugar.text.contains("universidad central") -> moverCamaraPorLatLongZoom(centralLatLang, zoom)
                    txtBuscarLugar.text.contains("uce") -> moverCamaraPorLatLongZoom(centralLatLang, zoom)
                    txtBuscarLugar.text.contains("universidad tecnologica equinoccional") -> moverCamaraPorLatLongZoom(uteLatLang, zoom)
                    txtBuscarLugar.text.contains("ute") -> moverCamaraPorLatLongZoom(uteLatLang, zoom)
                    txtBuscarLugar.text.contains("universidad de las americas") -> moverCamaraPorLatLongZoom(udlaLatLang, zoom)
                    txtBuscarLugar.text.contains("udla") -> moverCamaraPorLatLongZoom(udlaLatLang, zoom)
                    txtBuscarLugar.text.contains("universidad san francisco") -> moverCamaraPorLatLongZoom(usfqLatLang, zoom)
                    txtBuscarLugar.text.contains("usfq") -> moverCamaraPorLatLongZoom(usfqLatLang, zoom)
                    txtBuscarLugar.text.contains("multicines recreo") -> moverCamaraPorLatLongZoom(multicinesRecreo, zoom)
                    txtBuscarLugar.text.contains("multicines cci") -> moverCamaraPorLatLongZoom(multicinesCCI, zoom)
                    txtBuscarLugar.text.contains("multicines quicentro") -> moverCamaraPorLatLongZoom(multicinesQS, zoom)
                    txtBuscarLugar.text.contains("supercines quicentro") -> moverCamaraPorLatLongZoom(supercinesQS, zoom)
                    txtBuscarLugar.text.contains("supercines seis de diciembre") -> moverCamaraPorLatLongZoom(supercines6D, zoom)
                    txtBuscarLugar.text.contains("cinemark") -> moverCamaraPorLatLongZoom(cineMark, zoom)
                    txtBuscarLugar.text.contains("parque metropolitano") -> moverCamaraPorLatLongZoom(parqueMetropolitano, zoom)
                    txtBuscarLugar.text.contains("parque las cuadras") -> moverCamaraPorLatLongZoom(parquelasCuadras, zoom)
                    txtBuscarLugar.text.contains("parque ejido") -> moverCamaraPorLatLongZoom(parqueEjido, zoom)
                    txtBuscarLugar.text.contains("parque carolina") -> moverCamaraPorLatLongZoom(parqueCarolina, zoom)
                    txtBuscarLugar.text.contains("parque bicentenario") -> moverCamaraPorLatLongZoom(parqueBicentenario, zoom)
                }

            }
        }
    }



}