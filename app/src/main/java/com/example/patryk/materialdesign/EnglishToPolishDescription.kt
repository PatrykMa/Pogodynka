package com.example.patryk.materialdesign

enum class EnglishToPolishDescription(val eng:String, val pol:String) {
    ClearSky("clear sky", "Bezchmurnie"),
    Clouds("scattered clouds", "Niewielkie zachmurzenie"),
    BrokenClouds("broken clouds", "oberwanie chmury"),
    OverCastClouds("overcast clouds","Pochmurnie"),
    LightRain("light rain", "Lekki Deszczyk"),
    ModerateRain("moderate rain", "Umiarkowany deszcz"),
    LightSnow("light snow", "Lekki śnieg"),
    FewClouds("few clouds","Kilka Chmurek");

    companion object {
        fun getFromEng(engVal: String): EnglishToPolishDescription? {
            values().forEach {
                if(it.eng == engVal) return it
            }
            return null
        }
    }

}