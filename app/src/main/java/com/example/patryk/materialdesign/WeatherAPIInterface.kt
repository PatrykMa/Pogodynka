package com.example.patryk.materialdesign

interface WeatherAPIInterface {
    fun getDayWeatherByCity(name:String):WeatherInfo
    fun getDayWeatherByLocalisation(lon:String,lat:String):WeatherInfo
    fun getFutureWeatherByCity(name:String):Array<DayWeather>
    fun getFutureWeatherByLocalisation(lon:String,lat:String):Array<DayWeather>
}