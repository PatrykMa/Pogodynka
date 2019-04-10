package com.example.patryk.materialdesign

import android.content.Context


class WeatherManager(var context: Context) {
    val weatherApi:WeatherAPIInterface = WeatherAPIManager()

    var obserwableCities:Array<WeatherInfo>
        get(){
            //var array = arrayOf<WeatherInfo>(WeatherAPIManager().getCurrentWeatherByCityName("Katowice"),
             //   WeatherAPIManager().getCurrentWeatherByLocation("20.0","20.0").apply { cityName= "Twoja Lokalizacja" }
             //   )

            val cityWeather = mutableListOf<WeatherInfo>()
            DatabaseManager.citys.forEach {
                cityWeather.add(weatherApi.getDayWeatherByCity(it))
            }
            return cityWeather.toTypedArray()
        }

        /*{return arrayOf(

            WeatherInfo().apply { cityName= "Londoon";weatherDescription="Wietrznie";currentTemp=10F },
            WeatherInfo().apply { cityName= "Gliwice";weatherDescription="Mrzawka";currentTemp=8F },
            WeatherInfo().apply { cityName= "Katowice";weatherDescription="Deszcz";currentTemp=12F },
            WeatherInfo().apply { cityName= "Paryż";weatherDescription="Mróz";currentTemp=0F },
            WeatherInfo().apply { cityName= "Moskwa";weatherDescription="Słonecznie";currentTemp=-20F }
        )}*/
    set(value) {}

    var mainCity :Array<DayWeather>
    get() {
        if(DatabaseManager.usingLocalization && GPSPermissionManager.hasGpsPermission(context)) {
            val lonLat = GPSPermissionManager.getLonAndLatCurLocalzaion(context)
            return weatherApi.getFutureWeatherByLocalisation(lonLat.first.toString(),lonLat.second.toString())
        }
        return weatherApi.getFutureWeatherByCity(DatabaseManager.mainCity)
    }
    set(value) {}

        /*arrayOf(DayWeather().apply { day=WeatherInfo().apply {time ="14:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=10F;cloud=20;
                 humidity=60;time="Niedziela";windSpeed=45F }
                 houers= arrayOf(
                     WeatherInfo().apply {time="15:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=10F;cloud=20;
                         humidity=60;date="Niedziela";windSpeed=45F },
                     WeatherInfo().apply {time="16:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=11F;cloud=20;
                         humidity=60;date="Niedziela";windSpeed=45F },
                     WeatherInfo().apply {time="17:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=12F;cloud=20;
                         humidity=60;date="Niedziela";windSpeed=45F },
                     WeatherInfo().apply {time="18:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=9F;cloud=20;
                         humidity=60;date="Niedziela";windSpeed=45F },
                     WeatherInfo().apply {time="20:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=10F;cloud=20;
                         humidity=60;date="Niedziela";windSpeed=45F },
                     WeatherInfo().apply {time="21:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=10F;cloud=20;
                         humidity=60;date="Niedziela";windSpeed=45F },
                     WeatherInfo().apply {time="22:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=10F;cloud=20;
                         humidity=60;date="Niedziela";windSpeed=45F },
                     WeatherInfo().apply {time="23:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=10F;cloud=20;
                         humidity=60;date="Niedziela";windSpeed=45F }
                 )},
            DayWeather().apply { day=WeatherInfo().apply {time ="14:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=10F;cloud=20;
        humidity=60;time="Niedziela";windSpeed=45F }
        houers= arrayOf(
            WeatherInfo().apply {time="15:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=10F;cloud=20;
                humidity=60;date="Niedziela";windSpeed=45F },
            WeatherInfo().apply {time="16:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=11F;cloud=20;
                humidity=60;date="Niedziela";windSpeed=45F },
            WeatherInfo().apply {time="17:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=12F;cloud=20;
                humidity=60;date="Niedziela";windSpeed=45F },
            WeatherInfo().apply {time="18:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=9F;cloud=20;
                humidity=60;date="Niedziela";windSpeed=45F },
            WeatherInfo().apply {time="20:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=10F;cloud=20;
                humidity=60;date="Niedziela";windSpeed=45F },
            WeatherInfo().apply {time="21:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=10F;cloud=20;
                humidity=60;date="Niedziela";windSpeed=45F },
            WeatherInfo().apply {time="22:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=10F;cloud=20;
                humidity=60;date="Niedziela";windSpeed=45F },
            WeatherInfo().apply {time="23:00";cityName= "Ożarowice";weatherDescription="Słonecznie";currentTemp=10F;cloud=20;
                humidity=60;date="Niedziela";windSpeed=45F }
        )}

    )*/


}