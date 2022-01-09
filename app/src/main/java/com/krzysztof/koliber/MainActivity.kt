package com.krzysztof.koliber

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.*
import com.google.android.gms.location.FusedLocationProviderClient
import org.json.JSONException
import java.util.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private lateinit var tempText: TextView
    private lateinit var aqiText: TextView
    private lateinit var windText: TextView
    private lateinit var locationText: TextView
    private lateinit var pressureText: TextView
    private lateinit var humidityText: TextView
    private lateinit var pm25Text: TextView
    private lateinit var pm10Text: TextView
    private lateinit var o3Text: TextView
    private lateinit var no2Text: TextView
    private lateinit var so2Text: TextView
    private lateinit var coText: TextView
    private lateinit var indexDescription: TextView

    private lateinit var loadingView: LinearLayout
    private lateinit var mainView: LinearLayout
    private lateinit var bottomView: LinearLayout
    private lateinit var infoBtn: LinearLayout
    private lateinit var mapsBtn: LinearLayout

    private lateinit var cardWeather: CardView
    private lateinit var cardAqi: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempText = findViewById(R.id.temp_text)
        aqiText = findViewById(R.id.aqi_text)
        windText = findViewById(R.id.weather_wind)
        locationText = findViewById(R.id.location_text)
        pressureText = findViewById(R.id.weather_pressure)
        humidityText = findViewById(R.id.weather_humidity)
        pm25Text = findViewById(R.id.pm25)
        pm10Text = findViewById(R.id.pm10)
        o3Text = findViewById(R.id.o3)
        so2Text = findViewById(R.id.so2)
        no2Text = findViewById(R.id.no2)
        coText = findViewById(R.id.co)
        indexDescription = findViewById(R.id.index_description)

        loadingView = findViewById(R.id.loading_view)
        mainView = findViewById(R.id.main_view)
        bottomView = findViewById(R.id.bottom_view)
        infoBtn = findViewById(R.id.info_button)
        mapsBtn = findViewById(R.id.button_maps)

        cardWeather = findViewById(R.id.card_weather)
        cardAqi = findViewById(R.id.card_aqi)

        loadingView.visibility = View.VISIBLE
        mainView.visibility = View.GONE
        bottomView.visibility = View.GONE

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        infoBtn.setOnClickListener{
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }

        mapsBtn.setOnClickListener{
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        cardWeather.setOnClickListener{
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
        }

        cardAqi.setOnClickListener{
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
        }

        //Check permissions
        checkPermissions()
    }

    private fun checkPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //Request permissions if not yet granted
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1
            )
        } else {
            //Get location of user if permissions already granted
            getLocations()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocations() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            if (it == null) {

                //FusedLocationProviderClient is providing last known location
                //In case if last location is unknown here is check to get current location using LocationRequest & LocationCallback

                Toast.makeText(
                    this,
                    "Location unavailable, we need to create the location",
                    Toast.LENGTH_SHORT
                ).show()

                val locationRequest = LocationRequest.create()
                locationRequest.interval = 6000
                locationRequest.fastestInterval = 5000
                locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

                val locationCallback: LocationCallback

                locationCallback = object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult) {
                        super.onLocationResult(locationResult)
                        for (location in locationResult.locations) {
                            if (location != null) {

                                //After creating the location pass latitude & longitude arguments
                                val latitude = location.latitude
                                val longitude = location.longitude

                                readWeather(latitude, longitude)
                                readAqiInformation(latitude, longitude)
                            }
                        }
                    }
                }

                LocationServices.getFusedLocationProviderClient(this)
                    .requestLocationUpdates(locationRequest, locationCallback, null)

            } else it.apply {

                //When last location is known get latitude & longitude and pass the arguments to functions
                val latitude = it.latitude
                val longitude = it.longitude

                readWeather(latitude, longitude)
                readAqiInformation(latitude, longitude)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                    getLocations()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun readAqiInformation(latitude: Double, longitude: Double) {

        //Current AQI status from aqicn.org
        //API key is necessary to make it work
        //Create Common.kt file and place your API key there as const val within companion object (read GitHub readme for details)

        val baseUrl = "https://api.waqi.info/feed/geo:"
        val lat = latitude.toString()
        val lon = longitude.toString()
        val apiKey = Common.AQICN_API_KEY
        val url = "$baseUrl$lat;$lon/?token=$apiKey"

        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                val data = response.getJSONObject("data")
                val aqi = data.getInt("aqi")
                val iaqi = data.getJSONObject("iaqi")

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

                aqiText.text = "Air Quality Index: $aqi"

                when {
                    aqi <= 50 -> {
                        indexDescription.text = "Air quality is considered satisfactory, and air pollution poses little or no risk"
                    }
                    aqi in 51..100 -> {
                        indexDescription.text = "Air quality is acceptable; however, for some pollutants there may be a moderate health concern for a very small number of people who are unusually sensitive to air pollution."
                    }
                    aqi in 101..150 -> {
                        indexDescription.text = "Members of sensitive groups may experience health effects. The general public is not likely to be affected."
                    }
                    aqi in 151..200 -> {
                        indexDescription.text = "Everyone may begin to experience health effects; members of sensitive groups may experience more serious health effects"
                    }
                    aqi in 201..300 -> {
                        indexDescription.text = "Health warnings of emergency conditions. The entire population is more likely to be affected."
                    }
                    aqi in 301..500 -> {
                        indexDescription.text = "Health alert: everyone may experience more serious health effects"
                    }
                }

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error -> error.printStackTrace() })
        Volley.newRequestQueue(this).add(request)
    }

    private fun readWeather(latitude: Double, longitude: Double) {

        //Current weather from openweathermap.org
        //API key is necessary to make it work
        //Create Common.kt file and place your API key there as const val within companion object (read GitHub readme for details)
        //Metric units set as default. For another please read openweathermap.org API documentation
        //Location based on the coordinates

        val baseUrl = "https://api.openweathermap.org/data/2.5/weather?lat="
        val lat = latitude.toString()
        val lon = longitude.toString()
        val apiKey = Common.OPENWEATHERMAP_API_KEY
        val unit = "&units=metric"
        val url = "$baseUrl$lat&lon=$lon&appid=$apiKey$unit"

        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                val main = response.getJSONObject("main")
                val weather = response.getJSONArray("weather")
                val wind = response.getJSONObject("wind")
                val locationName = response.getString("name")

                val temp = main.getDouble("temp")
                val pressure = main.getInt("pressure")
                val humidity = main.getInt("humidity")
                val weatherObject = weather.getJSONObject(0)
                val windSpeed = wind.getDouble("speed")
                val description = weatherObject.getString("description")

                tempText.text = temp.roundToInt().toString() + "°C " + description

                //TODO: create convert methods to imperial units
                windText.text = "$windSpeed m/s"
                locationText.text = locationName
                pressureText.text = "$pressure hpa"
                humidityText.text = "$humidity%"

                loadingView.visibility = View.GONE
                mainView.visibility = View.VISIBLE
                bottomView.visibility = View.VISIBLE

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error -> error.printStackTrace() })
        Volley.newRequestQueue(this).add(request)
    }
}