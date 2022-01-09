package com.krzysztof.koliber

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import org.json.JSONException
import java.text.SimpleDateFormat
import kotlin.math.roundToInt

class WeatherActivity : AppCompatActivity() {

    private lateinit var chartView: AAChartView
    private lateinit var chartViewWeather: AAChartView

    private lateinit var sourceTextView1: TextView
    private lateinit var sourceTextView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = this.resources.getColor(R.color.font)

        chartView = findViewById(R.id.chart_view)
        chartViewWeather = findViewById(R.id.chart_view_weather)

        sourceTextView1 = findViewById(R.id.source_text1)
        sourceTextView2 = findViewById(R.id.source_text2)

        getWeatherForecast()

        getAqiForecast()

    }

    private fun getWeatherForecast() {
        val baseUrl =
            "https://api.openweathermap.org/data/2.5/forecast?lat=50.0646501&lon=19.9449799&appid="
        val apiKey = Common.OPENWEATHERMAP_API_KEY
        val url = "$baseUrl$apiKey&units=metric"
        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                val list = response.getJSONArray("list")

                val list0 = list.getJSONObject(0)
                val list1 = list.getJSONObject(1)
                val list2 = list.getJSONObject(2)
                val list3 = list.getJSONObject(3)
                val list4 = list.getJSONObject(4)
                val list5 = list.getJSONObject(5)
                val list6 = list.getJSONObject(6)
                val list7 = list.getJSONObject(7)
                val list8 = list.getJSONObject(8)
                val list9 = list.getJSONObject(9)
                val list10 = list.getJSONObject(10)
                val list11 = list.getJSONObject(11)
                val list12 = list.getJSONObject(12)
                val list13 = list.getJSONObject(13)
                val list14 = list.getJSONObject(14)
                val list15 = list.getJSONObject(15)
                val list16 = list.getJSONObject(16)
                val list17 = list.getJSONObject(17)
                val list18 = list.getJSONObject(18)
                val list19 = list.getJSONObject(19)
                val list20 = list.getJSONObject(20)
                val list21 = list.getJSONObject(21)
                val list22 = list.getJSONObject(22)
                val list23 = list.getJSONObject(23)
                val list24 = list.getJSONObject(24)
                val list25 = list.getJSONObject(25)
                val list26 = list.getJSONObject(26)
                val list27 = list.getJSONObject(27)
                val list28 = list.getJSONObject(28)
                val list29 = list.getJSONObject(29)
                val list30 = list.getJSONObject(30)
                val list31 = list.getJSONObject(31)
                val list32 = list.getJSONObject(32)
                val list33 = list.getJSONObject(33)
                val list34 = list.getJSONObject(34)

                val main0 = list0.getJSONObject("main")
                val main1 = list1.getJSONObject("main")
                val main2 = list2.getJSONObject("main")
                val main3 = list3.getJSONObject("main")
                val main4 = list4.getJSONObject("main")
                val main5 = list5.getJSONObject("main")
                val main6 = list6.getJSONObject("main")
                val main7 = list7.getJSONObject("main")
                val main8 = list8.getJSONObject("main")
                val main9 = list9.getJSONObject("main")
                val main10 = list10.getJSONObject("main")
                val main11 = list11.getJSONObject("main")
                val main12 = list12.getJSONObject("main")
                val main13 = list13.getJSONObject("main")
                val main14 = list14.getJSONObject("main")
                val main15 = list15.getJSONObject("main")
                val main16 = list16.getJSONObject("main")
                val main17 = list17.getJSONObject("main")
                val main18 = list18.getJSONObject("main")
                val main19 = list19.getJSONObject("main")
                val main20 = list20.getJSONObject("main")
                val main21 = list21.getJSONObject("main")
                val main22 = list22.getJSONObject("main")
                val main23 = list23.getJSONObject("main")
                val main24 = list24.getJSONObject("main")
                val main25 = list25.getJSONObject("main")
                val main26 = list26.getJSONObject("main")
                val main27 = list27.getJSONObject("main")
                val main28 = list28.getJSONObject("main")
                val main29 = list29.getJSONObject("main")
                val main30 = list30.getJSONObject("main")
                val main31 = list31.getJSONObject("main")
                val main32 = list32.getJSONObject("main")
                val main33 = list33.getJSONObject("main")
                val main34 = list34.getJSONObject("main")

                //Wind
                val wind0 = list0.getJSONObject("wind")
                val wind1 = list1.getJSONObject("wind")
                val wind2 = list2.getJSONObject("wind")
                val wind3 = list3.getJSONObject("wind")
                val wind4 = list4.getJSONObject("wind")
                val wind5 = list5.getJSONObject("wind")
                val wind6 = list6.getJSONObject("wind")
                val wind7 = list7.getJSONObject("wind")
                val wind8 = list8.getJSONObject("wind")
                val wind9 = list9.getJSONObject("wind")
                val wind10 = list10.getJSONObject("wind")
                val wind11 = list11.getJSONObject("wind")
                val wind12 = list12.getJSONObject("wind")
                val wind13 = list13.getJSONObject("wind")
                val wind14 = list14.getJSONObject("wind")
                val wind15 = list15.getJSONObject("wind")
                val wind16 = list16.getJSONObject("wind")
                val wind17 = list17.getJSONObject("wind")
                val wind18 = list18.getJSONObject("wind")
                val wind19 = list19.getJSONObject("wind")
                val wind20 = list20.getJSONObject("wind")
                val wind21 = list21.getJSONObject("wind")
                val wind22 = list22.getJSONObject("wind")
                val wind23 = list23.getJSONObject("wind")
                val wind24 = list24.getJSONObject("wind")
                val wind25 = list25.getJSONObject("wind")
                val wind26 = list26.getJSONObject("wind")
                val wind27 = list27.getJSONObject("wind")
                val wind28 = list28.getJSONObject("wind")
                val wind29 = list29.getJSONObject("wind")
                val wind30 = list30.getJSONObject("wind")
                val wind31 = list31.getJSONObject("wind")
                val wind32 = list32.getJSONObject("wind")
                val wind33 = list33.getJSONObject("wind")
                val wind34 = list34.getJSONObject("wind")

                //Wind speed
                val speed0 = wind0.getDouble("speed")
                val speed1 = wind1.getDouble("speed")
                val speed2 = wind2.getDouble("speed")
                val speed3 = wind3.getDouble("speed")
                val speed4 = wind4.getDouble("speed")
                val speed5 = wind5.getDouble("speed")
                val speed6 = wind6.getDouble("speed")
                val speed7 = wind7.getDouble("speed")
                val speed8 = wind8.getDouble("speed")
                val speed9 = wind9.getDouble("speed")
                val speed10 = wind10.getDouble("speed")
                val speed11 = wind11.getDouble("speed")
                val speed12 = wind12.getDouble("speed")
                val speed13 = wind13.getDouble("speed")
                val speed14 = wind14.getDouble("speed")
                val speed15 = wind15.getDouble("speed")
                val speed16 = wind16.getDouble("speed")
                val speed17 = wind17.getDouble("speed")
                val speed18 = wind18.getDouble("speed")
                val speed19 = wind19.getDouble("speed")
                val speed20 = wind20.getDouble("speed")
                val speed21 = wind21.getDouble("speed")
                val speed22 = wind22.getDouble("speed")
                val speed23 = wind23.getDouble("speed")
                val speed24 = wind24.getDouble("speed")
                val speed25 = wind25.getDouble("speed")
                val speed26 = wind26.getDouble("speed")
                val speed27 = wind27.getDouble("speed")
                val speed28 = wind28.getDouble("speed")
                val speed29 = wind29.getDouble("speed")
                val speed30 = wind30.getDouble("speed")
                val speed31 = wind31.getDouble("speed")
                val speed32 = wind32.getDouble("speed")
                val speed33 = wind33.getDouble("speed")
                val speed34 = wind34.getDouble("speed")

                //Datetime stamp
                val dt0 = list0.getString("dt_txt")
                val dt1 = list1.getString("dt_txt")
                val dt2 = list2.getString("dt_txt")
                val dt3 = list3.getString("dt_txt")
                val dt4 = list4.getString("dt_txt")
                val dt5 = list5.getString("dt_txt")
                val dt6 = list6.getString("dt_txt")
                val dt7 = list7.getString("dt_txt")
                val dt8 = list8.getString("dt_txt")
                val dt9 = list9.getString("dt_txt")
                val dt10 = list10.getString("dt_txt")
                val dt11 = list11.getString("dt_txt")
                val dt12 = list12.getString("dt_txt")
                val dt13 = list13.getString("dt_txt")
                val dt14 = list14.getString("dt_txt")
                val dt15 = list15.getString("dt_txt")
                val dt16 = list16.getString("dt_txt")
                val dt17 = list17.getString("dt_txt")
                val dt18 = list18.getString("dt_txt")
                val dt19 = list19.getString("dt_txt")
                val dt20 = list20.getString("dt_txt")
                val dt21 = list21.getString("dt_txt")
                val dt22 = list22.getString("dt_txt")
                val dt23 = list23.getString("dt_txt")
                val dt24 = list24.getString("dt_txt")
                val dt25 = list25.getString("dt_txt")
                val dt26 = list26.getString("dt_txt")
                val dt27 = list27.getString("dt_txt")
                val dt28 = list28.getString("dt_txt")
                val dt29 = list29.getString("dt_txt")
                val dt30 = list30.getString("dt_txt")
                val dt31 = list31.getString("dt_txt")
                val dt32 = list32.getString("dt_txt")
                val dt33 = list33.getString("dt_txt")
                val dt34 = list34.getString("dt_txt")

                //Temperature
                val temp0 = main0.getDouble("temp").roundToInt()
                val temp1 = main1.getDouble("temp").roundToInt()
                val temp2 = main2.getDouble("temp").roundToInt()
                val temp3 = main3.getDouble("temp").roundToInt()
                val temp4 = main4.getDouble("temp").roundToInt()
                val temp5 = main5.getDouble("temp").roundToInt()
                val temp6 = main6.getDouble("temp").roundToInt()
                val temp7 = main7.getDouble("temp").roundToInt()
                val temp8 = main8.getDouble("temp").roundToInt()
                val temp9 = main9.getDouble("temp").roundToInt()
                val temp10 = main10.getDouble("temp").roundToInt()
                val temp11 = main11.getDouble("temp").roundToInt()
                val temp12 = main12.getDouble("temp").roundToInt()
                val temp13 = main13.getDouble("temp").roundToInt()
                val temp14 = main14.getDouble("temp").roundToInt()
                val temp15 = main15.getDouble("temp").roundToInt()
                val temp16 = main16.getDouble("temp").roundToInt()
                val temp17 = main17.getDouble("temp").roundToInt()
                val temp18 = main18.getDouble("temp").roundToInt()
                val temp19 = main19.getDouble("temp").roundToInt()
                val temp20 = main20.getDouble("temp").roundToInt()
                val temp21 = main21.getDouble("temp").roundToInt()
                val temp22 = main22.getDouble("temp").roundToInt()
                val temp23 = main23.getDouble("temp").roundToInt()
                val temp24 = main24.getDouble("temp").roundToInt()
                val temp25 = main25.getDouble("temp").roundToInt()
                val temp26 = main26.getDouble("temp").roundToInt()
                val temp27 = main27.getDouble("temp").roundToInt()
                val temp28 = main28.getDouble("temp").roundToInt()
                val temp29 = main29.getDouble("temp").roundToInt()
                val temp30 = main30.getDouble("temp").roundToInt()
                val temp31 = main31.getDouble("temp").roundToInt()
                val temp32 = main32.getDouble("temp").roundToInt()
                val temp33 = main33.getDouble("temp").roundToInt()
                val temp34 = main34.getDouble("temp").roundToInt()

                val humidity0 = main0.getDouble("humidity").roundToInt()
                val humidity1 = main1.getDouble("humidity").roundToInt()
                val humidity2 = main2.getDouble("humidity").roundToInt()
                val humidity3 = main3.getDouble("humidity").roundToInt()
                val humidity4 = main4.getDouble("humidity").roundToInt()
                val humidity5 = main5.getDouble("humidity").roundToInt()
                val humidity6 = main6.getDouble("humidity").roundToInt()
                val humidity7 = main7.getDouble("humidity").roundToInt()
                val humidity8 = main8.getDouble("humidity").roundToInt()
                val humidity9 = main9.getDouble("humidity").roundToInt()
                val humidity10 = main10.getDouble("humidity").roundToInt()
                val humidity11 = main11.getDouble("humidity").roundToInt()
                val humidity12 = main12.getDouble("humidity").roundToInt()
                val humidity13 = main13.getDouble("humidity").roundToInt()
                val humidity14 = main14.getDouble("humidity").roundToInt()
                val humidity15 = main15.getDouble("humidity").roundToInt()
                val humidity16 = main16.getDouble("humidity").roundToInt()
                val humidity17 = main17.getDouble("humidity").roundToInt()
                val humidity18 = main18.getDouble("humidity").roundToInt()
                val humidity19 = main19.getDouble("humidity").roundToInt()
                val humidity20 = main20.getDouble("humidity").roundToInt()
                val humidity21 = main21.getDouble("humidity").roundToInt()
                val humidity22 = main22.getDouble("humidity").roundToInt()
                val humidity23 = main23.getDouble("humidity").roundToInt()
                val humidity24 = main24.getDouble("humidity").roundToInt()
                val humidity25 = main25.getDouble("humidity").roundToInt()
                val humidity26 = main26.getDouble("humidity").roundToInt()
                val humidity27 = main27.getDouble("humidity").roundToInt()
                val humidity28 = main28.getDouble("humidity").roundToInt()
                val humidity29 = main29.getDouble("humidity").roundToInt()
                val humidity30 = main30.getDouble("humidity").roundToInt()
                val humidity31 = main31.getDouble("humidity").roundToInt()
                val humidity32 = main32.getDouble("humidity").roundToInt()
                val humidity33 = main33.getDouble("humidity").roundToInt()
                val humidity34 = main34.getDouble("humidity").roundToInt()

                val aaChartModel: AAChartModel = AAChartModel()
                    .chartType(AAChartType.Areaspline)
                    .backgroundColor(R.color.font)
                    .dataLabelsEnabled(true)
                    .xAxisLabelsEnabled(false)
                    .dataLabelsEnabled(false)
                    .axesTextColor("#FFFFFF")
                    .categories(arrayOf(dt0, dt1, dt2, dt3, dt4, dt5, dt6, dt7, dt8, dt9, dt10, dt11, dt12, dt13, dt14, dt15, dt16, dt17, dt18, dt19, dt20, dt21, dt22, dt23, dt24, dt25, dt26, dt27, dt28, dt29, dt30, dt31, dt32, dt33, dt34))
                    .series(
                        arrayOf(
                            AASeriesElement()
                                .name("Humidity %")
                                .data(
                                    arrayOf(
                                        humidity0,
                                        humidity1,
                                        humidity2,
                                        humidity3,
                                        humidity4,
                                        humidity5,
                                        humidity6,
                                        humidity7,
                                        humidity8,
                                        humidity9,
                                        humidity10,
                                        humidity11,
                                        humidity12,
                                        humidity13,
                                        humidity14,
                                        humidity15,
                                        humidity16,
                                        humidity17,
                                        humidity18,
                                        humidity19,
                                        humidity20,
                                        humidity21,
                                        humidity22,
                                        humidity23,
                                        humidity24,
                                        humidity25,
                                        humidity26,
                                        humidity27,
                                        humidity28,
                                        humidity29,
                                        humidity30,
                                        humidity31,
                                        humidity32,
                                        humidity33,
                                        humidity34
                                    )
                                ),
                            AASeriesElement()
                                .name("Temperature °C")
                                .data(
                                    arrayOf(
                                        temp0,
                                        temp1,
                                        temp2,
                                        temp3,
                                        temp4,
                                        temp5,
                                        temp6,
                                        temp7,
                                        temp8,
                                        temp9,
                                        temp10,
                                        temp11,
                                        temp12,
                                        temp13,
                                        temp14,
                                        temp15,
                                        temp16,
                                        temp17,
                                        temp18,
                                        temp19,
                                        temp20,
                                        temp21,
                                        temp22,
                                        temp23,
                                        temp24,
                                        temp25,
                                        temp26,
                                        temp27,
                                        temp28,
                                        temp29,
                                        temp30,
                                        temp31,
                                        temp32,
                                        temp33,
                                        temp34
                                    )
                                ),
                            AASeriesElement()
                                .name("Wind m/s")
                                .data(
                                    arrayOf(
                                        speed0,
                                        speed1,
                                        speed2,
                                        speed3,
                                        speed4,
                                        speed5,
                                        speed6,
                                        speed7,
                                        speed8,
                                        speed9,
                                        speed10,
                                        speed11,
                                        speed12,
                                        speed13,
                                        speed14,
                                        speed15,
                                        speed16,
                                        speed17,
                                        speed18,
                                        speed19,
                                        speed20,
                                        speed21,
                                        speed22,
                                        speed23,
                                        speed24,
                                        speed25,
                                        speed26,
                                        speed27,
                                        speed28,
                                        speed29,
                                        speed30,
                                        speed31,
                                        speed32,
                                        speed33,
                                        speed34
                                    )
                                ),
                        )
                    )

                chartViewWeather.aa_drawChartWithChartModel(aaChartModel)

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error -> error.printStackTrace() })
        Volley.newRequestQueue(this).add(request)
    }

    private fun getAqiForecast() {

        val baseUrl =
            "https://api.waqi.info/feed/krakow/?token="

        val api_key = Common.AQICN_API_KEY
        val url = baseUrl + api_key

        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                val data = response.getJSONObject("data")

                //Graphing library is using Arrays.
                //Array is fixed size so it's impossible to add new elements dynamically.
                //We have to add them one by one.

                val forecast = data.getJSONObject("forecast")
                val daily = forecast.getJSONObject("daily")

                //Sources
                val attributions = data.getJSONArray("attributions")
                val attr1 = attributions.getJSONObject(0)
                val attr2 = attributions.getJSONObject(1)

                val source1 = attr1.getString("name")
                val source2 = attr2.getString("name")

                sourceTextView1.text = "1. $source1"
                sourceTextView2.text = "2. $source2"

                //AqiCn is providing in most of the locations 5 days forecast
                //Sometimes it's 7 days but here is 5 days to avoid errors

                //O3
                val o3 = daily.getJSONArray("o3")
                val dayOne = o3.getJSONObject(0)
                val dayTwo = o3.getJSONObject(1)
                val dayThree = o3.getJSONObject(2)
                val dayFour = o3.getJSONObject(3)
                val dayFive = o3.getJSONObject(4)

                val avgOne = dayOne.getInt("avg")
                val avgTwo = dayTwo.getInt("avg")
                val avgThree = dayThree.getInt("avg")
                val avgFour = dayFour.getInt("avg")
                val avgFive = dayFive.getInt("avg")

                //Date
                val dateOne = dayOne.getString("day")
                val dateTwo = dayTwo.getString("day")
                val dateThree = dayThree.getString("day")
                val dateFour = dayFour.getString("day")
                val dateFive = dayFive.getString("day")

                //PM10
                val pm10 = daily.getJSONArray("pm10")
                val dayOnePm10 = pm10.getJSONObject(0)
                val dayTwoPm10 = pm10.getJSONObject(1)
                val dayThreePm10 = pm10.getJSONObject(2)
                val dayFourPm10 = pm10.getJSONObject(3)
                val dayFivePm10 = pm10.getJSONObject(4)

                val avgOnePm10 = dayOnePm10.getInt("avg")
                val avgTwoPm10 = dayTwoPm10.getInt("avg")
                val avgThreePm10 = dayThreePm10.getInt("avg")
                val avgFourPm10 = dayFourPm10.getInt("avg")
                val avgFivePm10 = dayFivePm10.getInt("avg")

                //PM2.5
                val pm25 = daily.getJSONArray("pm25")
                val dayOnePm25 = pm25.getJSONObject(0)
                val dayTwoPm25 = pm25.getJSONObject(1)
                val dayThreePm25 = pm25.getJSONObject(2)
                val dayFourPm25 = pm25.getJSONObject(3)
                val dayFivePm25 = pm25.getJSONObject(4)

                val avgOnePm25 = dayOnePm25.getInt("avg")
                val avgTwoPm25 = dayTwoPm25.getInt("avg")
                val avgThreePm25 = dayThreePm25.getInt("avg")
                val avgFourPm25 = dayFourPm25.getInt("avg")
                val avgFivePm25 = dayFivePm25.getInt("avg")

                //UV Index
                val uvi = daily.getJSONArray("uvi")
                val dayOneUvi = uvi.getJSONObject(0)
                val dayTwoUvi = uvi.getJSONObject(1)
                val dayThreeUvi = uvi.getJSONObject(2)
                val dayFourUvi = uvi.getJSONObject(3)
                val dayFiveUvi = uvi.getJSONObject(4)

                val avgOneUvi = dayOneUvi.getInt("avg")
                val avgTwoUvi = dayTwoUvi.getInt("avg")
                val avgThreeUvi = dayThreeUvi.getInt("avg")
                val avgFourUvi = dayFourUvi.getInt("avg")
                val avgFiveUvi = dayFiveUvi.getInt("avg")

                val aaChartModel: AAChartModel = AAChartModel()
                    .chartType(AAChartType.Areaspline)
                    .backgroundColor(R.color.font)
                    .dataLabelsEnabled(true)
                    .axesTextColor("#FFFFFF")
                    .categories(arrayOf(dateOne, dateTwo, dateThree, dateFour, dateFive))
                    .series(
                        arrayOf(
                            AASeriesElement()
                                .name("PM2.5 μg/m3")
                                .data(
                                    arrayOf(
                                        avgOnePm25,
                                        avgTwoPm25,
                                        avgThreePm25,
                                        avgFourPm25,
                                        avgFivePm25
                                    )
                                ),
                            AASeriesElement()
                                .name("PM10 μg/m3")
                                .data(
                                    arrayOf(
                                        avgOnePm10,
                                        avgTwoPm10,
                                        avgThreePm10,
                                        avgFourPm10,
                                        avgFivePm10
                                    )
                                ),
                            AASeriesElement()
                                .name("O3 μg/m3")
                                .data(arrayOf(avgOne, avgTwo, avgThree, avgFour, avgFive)),
                            AASeriesElement()
                                .name("UV Index")
                                .data(
                                    arrayOf(
                                        avgOneUvi,
                                        avgTwoUvi,
                                        avgThreeUvi,
                                        avgFourUvi,
                                        avgFiveUvi
                                    )
                                ),
                        )
                    )

                chartView.aa_drawChartWithChartModel(aaChartModel)

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error -> error.printStackTrace() })
        Volley.newRequestQueue(this).add(request)
    }
}