package com.krzysztof.koliber

import android.content.ContentValues.TAG
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.krzysztof.koliber.databinding.ActivityMapsBinding
import org.json.JSONException

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var aqiText: TextView
    private lateinit var pm25Text: TextView
    private lateinit var pm10Text: TextView
    private lateinit var o3Text: TextView
    private lateinit var no2Text: TextView
    private lateinit var so2Text: TextView
    private lateinit var coText: TextView
    private lateinit var cardDetails: CardView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        aqiText = findViewById(R.id.aqi_text)
        pm25Text = findViewById(R.id.pm25_text)
        pm10Text = findViewById(R.id.pm10_text)
        o3Text = findViewById(R.id.o3_text)
        no2Text = findViewById(R.id.no2_text)
        so2Text = findViewById(R.id.so2_text)
        coText = findViewById(R.id.co_text)
        cardDetails = findViewById(R.id.card_details)
        progressBar = findViewById(R.id.progress_bar)

        progressBar.visibility = View.GONE
        cardDetails.visibility = View.GONE

    }

    private fun setMapStyle(map: GoogleMap) {
        try {
            // Customize the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this,
                    R.raw.map_style
                )
            )

            if (!success) {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, "Can't find style. Error: ", e)
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        setMapStyle(mMap)

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(50.0646501, 19.9449799)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))

        val cameraPosition = CameraPosition.Builder()
            .target(sydney)
            .zoom(10f)
            .build()

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        mMap.setOnCameraIdleListener() {
            //Stations on the map are added when camera is idle
            getStations()
        }

        mMap.setOnCameraMoveListener() {
            //When there are many markers on the map the app is lagging when moving
            //While moving the camera it's better for performance to clear the map
            mMap.clear()
        }

        mMap.setOnMarkerClickListener { marker ->
            progressBar.visibility = View.VISIBLE

            val position = marker.position
            val lat = position.latitude.toString()
            val lon = position.longitude.toString()

            getAqiInformation(lat, lon)

            false
        }

    }

    private fun getAqiInformation(lat: String, lon: String) {
        val baseUrl = "https://api.waqi.info/feed/geo:"
        val apiKey = Common.AQICN_API_KEY
        val url = "$baseUrl$lat;$lon/?token=$apiKey"

        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                val data = response.getJSONObject("data")
                val aqi = data.getInt("aqi")
                val iaqi = data.getJSONObject("iaqi")
                val city = data.getJSONObject("city")
                val name = city.getString("name")


                if (iaqi.has("pm25")) {
                    val pm25 = iaqi.getJSONObject("pm25")
                    val pm25Value = pm25.getInt("v")
                    pm25Text.text = "$pm25Value μg/m³"
                } else {
                    pm25Text.text = "-"
                }

                if (iaqi.has("pm10")) {
                    val pm10 = iaqi.getJSONObject("pm10")
                    val pm10Value = pm10.getInt("v")
                    pm10Text.text = "$pm10Value μg/m³"
                } else {
                    pm10Text.text = "-"
                }

                if (iaqi.has("o3")) {
                    val o3 = iaqi.getJSONObject("o3")
                    val o3Value = o3.getInt("v")
                    o3Text.text = "$o3Value μg/m³"
                } else {
                    o3Text.text = "-"
                }

                if (iaqi.has("no2")) {
                    val no2 = iaqi.getJSONObject("no2")
                    val no2Value = no2.getInt("v")
                    no2Text.text = "$no2Value μg/m³"
                } else {
                    no2Text.text = "-"
                }

                if (iaqi.has("so2")) {
                    val so2 = iaqi.getJSONObject("so2")
                    val so2Value = so2.getInt("v")
                    so2Text.text = "$so2Value μg/m³"
                } else {
                    so2Text.text = "-"
                }

                if (iaqi.has("co")) {
                    val co = iaqi.getJSONObject("co")
                    val coValue = co.getInt("v")
                    coText.text = "$coValue μg/m³"
                } else {
                    coText.text = "-"
                }

                aqiText.text = "$name: $aqi"

                cardDetails.visibility = View.VISIBLE
                progressBar.visibility = View.GONE

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error -> error.printStackTrace() })
        Volley.newRequestQueue(this).add(request)
    }


    private fun getStations() {
        //1: south, 2: east, 3: north, 4: west

        val visibleRegion = mMap.projection.visibleRegion
        val nearLeft = visibleRegion.nearLeft
        val nearRight = visibleRegion.nearRight
        val farLeft = visibleRegion.farLeft
        val farRight = visibleRegion.farRight

        val north = farLeft.latitude
        val south = nearRight.latitude
        val east = nearLeft.longitude
        val west = farRight.longitude

        val baseUrl = "https://api.waqi.info/map/bounds/?latlng="
        val apiKey = Common.AQICN_API_KEY
        val url = "$baseUrl$south,$east,$north,$west&token=$apiKey"

        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                val data = response.getJSONArray("data")
                for (i in 0 until data.length()) {
                    val point = data.getJSONObject(i)
                    val station = point.getJSONObject("station")
                    val stationName = station.getString("name")

                    val latitude = point.getDouble("lat")
                    val longitude = point.getDouble("lon")
                    val aqi = point.getString("aqi")

                    val stationLocation = LatLng(latitude, longitude)

                    when {
                        aqi.contains("-") -> {
                            mMap.addMarker(
                                MarkerOptions().position(stationLocation)
                                    .title("$stationName: Index not available")
                                    .icon(
                                        BitmapDescriptorFactory.defaultMarker(
                                            BitmapDescriptorFactory.HUE_CYAN
                                        )
                                    )
                            )
                        }
                        aqi.toInt() <= 50 -> {
                            mMap.addMarker(
                                MarkerOptions().position(stationLocation)
                                    .title("$stationName: $aqi")
                                    .icon(
                                        BitmapDescriptorFactory.defaultMarker(
                                            BitmapDescriptorFactory.HUE_GREEN
                                        )
                                    )
                            )
                        }
                        aqi.toInt() in 51..100 -> {
                            mMap.addMarker(
                                MarkerOptions().position(stationLocation)
                                    .title("$stationName: $aqi")
                                    .icon(
                                        BitmapDescriptorFactory.defaultMarker(
                                            BitmapDescriptorFactory.HUE_YELLOW
                                        )
                                    )
                            )
                        }
                        aqi.toInt() in 101..150 -> {
                            mMap.addMarker(
                                MarkerOptions().position(stationLocation)
                                    .title("$stationName: $aqi")
                                    .icon(
                                        BitmapDescriptorFactory.defaultMarker(
                                            BitmapDescriptorFactory.HUE_ORANGE
                                        )
                                    )
                            )
                        }
                        aqi.toInt() in 151..200 -> {
                            mMap.addMarker(
                                MarkerOptions().position(stationLocation)
                                    .title("$stationName: $aqi")
                                    .icon(
                                        BitmapDescriptorFactory.defaultMarker(
                                            BitmapDescriptorFactory.HUE_RED
                                        )
                                    )
                            )
                        }
                        aqi.toInt() in 201..300 -> {
                            mMap.addMarker(
                                MarkerOptions().position(stationLocation)
                                    .title("$stationName: $aqi")
                                    .icon(
                                        BitmapDescriptorFactory.defaultMarker(
                                            BitmapDescriptorFactory.HUE_VIOLET
                                        )
                                    )
                            )
                        }
                        aqi.toInt() in 301..500 -> {
                            mMap.addMarker(
                                MarkerOptions().position(stationLocation)
                                    .title("$stationName: $aqi")
                                    .icon(
                                        BitmapDescriptorFactory.defaultMarker(
                                            BitmapDescriptorFactory.HUE_ROSE
                                        )
                                    )
                            )
                        }
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error -> error.printStackTrace() })
        Volley.newRequestQueue(this).add(request)
    }
}