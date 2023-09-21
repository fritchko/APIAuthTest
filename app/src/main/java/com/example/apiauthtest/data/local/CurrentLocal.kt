package com.example.apiauthtest.data.local

data class CurrentLocal(

    val cloud: Int?,
    val condition: ConditionLocal?,
    val feelslikeC: Double?,
    val feelslikeF: Double?,
    val gustKph: Double?,
    val gustMph: Double?,
    val humidity: Int?,
    val isDay: Int?,
    val lastUpdated: String?,
    val lastUpdatedEpoch: Int?,
    val precipIn: Int?,
    val precipMm: Int?,
    val pressureIn: Double?,
    val pressureMb: Int?,
    val tempC: Double?,
    val tempF: Double?,
    val uv: Int?,
    val visKm: Int?,
    val visMiles: Int?,
    val windDegree: Int?,
    val windDir: String?,
    val windKph: Double?,
    val windMph: Double?
)
