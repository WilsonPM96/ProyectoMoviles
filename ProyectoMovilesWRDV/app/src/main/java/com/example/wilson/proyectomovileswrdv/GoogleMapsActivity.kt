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
import com.example.wilson.proyectomovileswrdv.Reservas.CrearReservaActivity
import com.example.wilson.proyectomovileswrdv.Usuario.Usuario
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
        GoogleMap.OnCameraIdleListener,
        GoogleMap.OnMarkerClickListener{

    private lateinit var mMap: GoogleMap

    var usuario: Usuario? = null
    var arregloLugares : ArrayList<Lugar> = BaseDatosLugar.getListofLugares()
    var arregloMarcadoresU = ArrayList<Marker>()
    var arregloMarcadoresC = ArrayList<Marker>()
    var arregloMarcadoresP = ArrayList<Marker>()


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


    var usuarioTienePermisosLocalizacion = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_maps)
        usuario = intent.getParcelableExtra("idUsuario")
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


            anadirMarcadorUniversidad(epnLatLang, "Escuela Politécnica Nacional")
            anadirMarcadorUniversidad(centralLatLang, "Universidad Central")
            anadirMarcadorUniversidad(uteLatLang, "Universidad Tecnológica Equinoccial")
            anadirMarcadorUniversidad(udlaLatLang, "Universidad de las Américas")
            anadirMarcadorUniversidad(usfqLatLang, "Universidad San Francisco de Quito")
            anadirMarcadorCine(multicinesRecreo, "Multicines Recreo")
            anadirMarcadorCine(multicinesCCI, "Multicines CCI")
            anadirMarcadorCine(multicinesQS, "Multicines Quicentro Shopping")
            anadirMarcadorCine(supercinesQS, "Supercines Quicentro Sur")
            anadirMarcadorCine(supercines6D, "Supercines 6 Diciembre")
            anadirMarcadorCine(cineMark, "Cinemark")
            anadirMarcadorParque(parqueMetropolitano, "Parque Metropolitano")
            anadirMarcadorParque(parquelasCuadras, "Parque las Cuadras")
            anadirMarcadorParque(parqueEjido, "Parque Ejido")
            anadirMarcadorParque(parqueCarolina, "Parque Carolina")
            anadirMarcadorParque(parqueBicentenario, "Parque Bicentenario")


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
            uiSettings.isRotateGesturesEnabled = true
        }
    }

    private fun anadirMarcadorUniversidad(latitudLongitud: LatLng, titulo: String) {


        arregloMarcadoresU = ArrayList<Marker>()


        val marker = mMap.addMarker(
                MarkerOptions()
                        .position(latitudLongitud)
                        .title(titulo)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        )

        arregloMarcadoresU.add(marker)

        Log.i("google-map", "$arregloMarcadoresU")
    }

    private fun anadirMarcadorCine(latitudLongitud: LatLng, titulo: String) {


        arregloMarcadoresC = ArrayList<Marker>()


        val marker = mMap.addMarker(
                MarkerOptions()
                        .position(latitudLongitud)
                        .title(titulo)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        )

        arregloMarcadoresC.add(marker)

        Log.i("google-map", "$arregloMarcadoresC")
    }

    private fun anadirMarcadorParque(latitudLongitud: LatLng, titulo: String) {


        arregloMarcadoresP = ArrayList<Marker>()


        val marker = mMap.addMarker(
                MarkerOptions()
                        .position(latitudLongitud)
                        .title(titulo)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        )

        arregloMarcadoresP.add(marker)

        Log.i("google-map", "$arregloMarcadoresP")
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
            setOnMarkerClickListener(this@GoogleMapsActivity)
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

    override fun onMarkerClick(p0: Marker?): Boolean {
        var pos = 0
        val intent= Intent(this, CrearReservaActivity::class.java)

        when{
            p0?.title.equals("Escuela Politécnica Nacional") -> pos=1
            p0?.title.equals("Universidad Central") -> pos=8
            p0?.title.equals("Universidad Tecnológica Equinoccial") -> pos=2
            p0?.title.equals("Universidad de las Américas") -> pos=3
            p0?.title.equals("Universidad San Francisco de Quito") -> pos=4

            p0?.title.equals("Multicines Recreo") -> pos=5
            p0?.title.equals("Multicines CCI") -> pos=6
            p0?.title.equals("Multicines Quicentro Shopping") -> pos=7
            p0?.title.equals("Supercines Quicentro Sur") -> pos=0
            p0?.title.equals("Supercines 6 Diciembre") -> pos=9
            p0?.title.equals("Cinemark") -> pos=10

            p0?.title.equals("Parque Metropolitano") -> pos=11
            p0?.title.equals("Parque las Cuadras") -> pos=12
            p0?.title.equals("Parque Ejido") -> pos=13
            p0?.title.equals("Parque Carolina") -> pos=14
            p0?.title.equals("Parque Bicentenario") -> pos=15
        }

        intent.putExtra("idLugar", arregloLugares[pos])
        intent.putExtra("idUsuario", usuario)
        startActivity(intent)
        finish()
        return true
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
                Toast.makeText(this, "Buscando "+txtBuscarLugar.text, Toast.LENGTH_SHORT).show()

                when{
                    txtBuscarLugar.text.contains("escuela politécnica nacional") -> moverCamaraPorLatLongZoom(epnLatLang, zoom)
                    txtBuscarLugar.text.contains("epn") -> moverCamaraPorLatLongZoom(epnLatLang, zoom)
                    txtBuscarLugar.text.contains("universidad central") -> moverCamaraPorLatLongZoom(centralLatLang, zoom)
                    txtBuscarLugar.text.contains("use") -> moverCamaraPorLatLongZoom(centralLatLang, zoom)
                    txtBuscarLugar.text.contains("universidad tecnológica equinoccial") -> moverCamaraPorLatLongZoom(uteLatLang, zoom)
                    txtBuscarLugar.text.contains("ute") -> moverCamaraPorLatLongZoom(uteLatLang, zoom)
                    txtBuscarLugar.text.contains("Universidad de las Américas") -> moverCamaraPorLatLongZoom(udlaLatLang, zoom)
                    txtBuscarLugar.text.contains("udla") -> moverCamaraPorLatLongZoom(udlaLatLang, zoom)
                    txtBuscarLugar.text.contains("universidad San Francisco") -> moverCamaraPorLatLongZoom(usfqLatLang, zoom)
                    txtBuscarLugar.text.contains("usfq") -> moverCamaraPorLatLongZoom(usfqLatLang, zoom)
                    txtBuscarLugar.text.contains("multicines recreo") -> moverCamaraPorLatLongZoom(multicinesRecreo, zoom)
                    txtBuscarLugar.text.contains("multicines cc") -> moverCamaraPorLatLongZoom(multicinesCCI, zoom)
                    txtBuscarLugar.text.contains("multicines quicentro") -> moverCamaraPorLatLongZoom(multicinesQS, zoom)
                    txtBuscarLugar.text.contains("supercines quicentro") -> moverCamaraPorLatLongZoom(supercinesQS, zoom)
                    txtBuscarLugar.text.contains("supercines 6 de diciembre") -> moverCamaraPorLatLongZoom(supercines6D, zoom)
                    txtBuscarLugar.text.contains("Cinemark") -> moverCamaraPorLatLongZoom(cineMark, zoom)
                    txtBuscarLugar.text.contains("parque Metropolitano") -> moverCamaraPorLatLongZoom(parqueMetropolitano, zoom)
                    txtBuscarLugar.text.contains("parque Las cuadras") -> moverCamaraPorLatLongZoom(parquelasCuadras, zoom)
                    txtBuscarLugar.text.contains("parque Ejido") -> moverCamaraPorLatLongZoom(parqueEjido, zoom)
                    txtBuscarLugar.text.contains("parque Carolina") -> moverCamaraPorLatLongZoom(parqueCarolina, zoom)
                    txtBuscarLugar.text.contains("Parque Bicentenario") -> moverCamaraPorLatLongZoom(parqueBicentenario, zoom)
                }

            }
        }
    }



}