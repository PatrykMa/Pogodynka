package com.example.patryk.materialdesign/*
import android.arch.persistence.room.*

@Entity
data class CityDatabase(
    @PrimaryKey var uid: Int,
    @ColumnInfo(name = "cityName") var cityName: String?
)/*/