package com.example.patryk.materialdesign

import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import java.net.URL
import java.util.*

class WeatherAPIManager : WeatherAPIInterface{

    companion object {
        private val key ="c454b501b54cc84f42b249e14457d121"
    }

    override fun getDayWeatherByCity(name: String): WeatherInfo {
        return getCurrentWeatherByCityName(name)
    }

    override fun getDayWeatherByLocalisation(lon: String, lat: String): WeatherInfo {
        return getCurrentWeatherByLocation(lon, lat)
    }

    override fun getFutureWeatherByCity(name: String): Array<DayWeather> {
        val toReturn = mutableListOf<DayWeather>()
        val everyone = getHoursWeatherByCityName(name)
        var currentDayName = ""
        var day:DayWeather? = null
        var hours = mutableListOf<WeatherInfo>()
        for(info in everyone)
        {
            if(currentDayName != info.date)
            {
                if( day != null){
                    day.houers = hours.toTypedArray()
                    toReturn.add(day )
                    hours = arrayListOf()
                }
                day = DayWeather()
                currentDayName = info.date
                day.day = info
            }
            hours.add(info)
        }

        return  toReturn.toTypedArray()
    }

    override fun getFutureWeatherByLocalisation(lon: String, lat: String): Array<DayWeather> {
        val toReturn = mutableListOf<DayWeather>()
        val everyone = getHoursWeatherByLocation(lon, lat)
        var currentDayName = ""
        var day:DayWeather? = null
        var hours = mutableListOf<WeatherInfo>()
        for(info in everyone)
        {
            if(currentDayName != info.date)
            {
                if( day != null){
                    day.houers = hours.toTypedArray()
                    toReturn.add(day )
                    hours = arrayListOf()

                }
                day = DayWeather()
                currentDayName = info.date
                day.day = info
            }
            hours.add(info)
        }

        return  toReturn.toTypedArray()
    }

    private fun getCurrentWeatherByCityName(name:String):WeatherInfo
    {

        val response = URL("https://api.openweathermap.org/data/2.5/weather?q=$name,pl&appid=$key").readText()
        val mainJS = JSONObject(response)
        try {
            val info = getDataFromMainJSObject(mainJS)
            info.cityName = mainJS["name"] as String
            return info
        } catch(e:Exception)
        {
            return WeatherInfo()
        }
    }

    private fun getCurrentWeatherByLocation(lon:String,lat:String):WeatherInfo
    {
        val response = URL("https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&appid=$key").readText()
        val mainJS = JSONObject(response)
        try {
            val info = getDataFromMainJSObject(mainJS)
            info.cityName = mainJS["name"] as String
            return info
        } catch(e:Exception)
        {
            return WeatherInfo()
        }
    }

    fun getHoursWeatherByCityName(name:String):Array<WeatherInfo>
    {
        val response = URL("https://api.openweathermap.org/data/2.5/forecast?q=$name,pl&appid=$key").readText()
        val mainJS = JSONObject(response)
        val cityJS = mainJS["city"] as JSONObject
        val arrayToReturn = mutableListOf<WeatherInfo>()
        try {
            val list = mainJS["list"] as JSONArray
            for(i in 0 until list.length())
            {
                arrayToReturn.add(getDataFromMainJSObject(list.get(i) as JSONObject).apply {
                    cityName = cityJS["name"] as String
                })
            }
        }
        catch (e:Exception) {
            return arrayOf()
        }
        return arrayToReturn.toTypedArray()
    }

    fun getHoursWeatherByLocation(lon:String, lat:String):Array<WeatherInfo>
    {
        val response = URL("https://api.openweathermap.org/data/2.5/forecast?lat=$lat&lon=$lon&appid=$key").readText()
        val mainJS = JSONObject(response)
        val cityJS = mainJS["city"] as JSONObject
        val arrayToReturn = mutableListOf<WeatherInfo>()
        try {
            val list = mainJS["list"] as JSONArray
            for(i in 0 until list.length())
            {
                arrayToReturn.add(getDataFromMainJSObject(list.get(i) as JSONObject).apply {
                    cityName = "Twoja Lokalizacja"
                })
            }
        }
        catch (e:Exception) {
            return arrayOf()
        }
        return arrayToReturn.toTypedArray()
    }

    private fun getDataFromMainJSObject(mainJS:JSONObject):WeatherInfo
    {
        val info = WeatherInfo()
        val weatherJS = mainJS["weather"] as JSONArray
        val mainMainJS = mainJS["main"] as JSONObject
        val cloudsJS = mainJS["clouds"] as JSONObject
        val windJS = mainJS["wind"] as JSONObject
        val time = Calendar.getInstance()
        time.timeInMillis = mainJS["dt"] as Int * 1000L

        info.time = time.get(Calendar.HOUR_OF_DAY).toString() + ":00"
        info.weatherDescription = "" + EnglishToPolishDescription.getFromEng(weatherJS.getJSONObject(0)["description"] as String)?.pol
        info.currentTemp = (try{mainMainJS["temp"] as Double} catch (e:Exception){(mainMainJS["temp"] as Int).toDouble()} - 273.15).toFloat()
        info.cloud = cloudsJS["all"] as Int
        info.humidity = (mainMainJS["humidity"] as Int)
        info.date = WeekDayName.getFromNumber(time.get(Calendar.DAY_OF_WEEK)).dayName
        info.windSpeed = try{(windJS["speed"] as Double).toFloat()} catch (e:Exception) {(windJS["speed"] as Int).toFloat()}
        return setIco(info)
    }

    private fun setIco(info: WeatherInfo):WeatherInfo
    {

            info.veatherType = WeatherIco.Cloudy
        if (info.weatherDescription == EnglishToPolishDescription.ClearSky.pol)
            info.veatherType = WeatherIco.Sunny
        return info
    }
}