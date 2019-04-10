package com.example.patryk.materialdesign

class DatabaseManager {
    companion object {
        var citys:Array<String>
            get() {return arrayOf("Katowice", "Zabrze", "Bytom")}
            set(value) {}
        var mainCity:String
            get() {return "Gliwice"}
            set(value) {}
        var usingLocalization:Boolean = true
            get() { return  field}
            set(value) {field = value}
        fun addCity(name:String)
        {

        }
        fun deleteCity(name: String){}
    }
}