package com.example.patryk.materialdesign

import java.lang.Exception

enum class WeekDayName(val dayNumber:Int,val dayName:String) {
    Sunday(1,"Niedziela"),
    Monday(2,"Poniedziałek"),
    Tuesday(3,"Wtorek"),
    Wednesday(4,"Środa"),
    Thursday(5,"Czwartek"),
    Friday(6,"Piątek"),
    Saturday(7, "Sobota");
    companion object {
      fun getFromNumber(num:Int):WeekDayName
      {
          values().forEach {
              if (it.dayNumber == num) return it
          }
          throw Exception("undefinde value")
      }
    }
}