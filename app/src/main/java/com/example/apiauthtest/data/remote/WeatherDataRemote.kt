package com.example.apiauthtest.data.remote


import com.example.apiauthtest.data.local.ConditionLocal
import com.example.apiauthtest.data.local.CurrentLocal
import com.example.apiauthtest.data.local.LocationLocal
import com.example.apiauthtest.data.local.WeatherDataLocal
import com.example.apiauthtest.network.ResponseWrapper
import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class WeatherDataRemote(
    @SerializedName("current")
    val currentRemote: CurrentRemote?,
    @SerializedName("location")
    val locationRemote: LocationRemote?
)

fun Response<WeatherDataRemote>.toWeatherDataLocal(): ResponseWrapper<WeatherDataLocal> {
    return if( this.isSuccessful ){
        val response = this.body()
        ResponseWrapper.Success(
            WeatherDataLocal(
                current = CurrentLocal(
                    cloud = response?.currentRemote?.cloud,
                    condition = ConditionLocal(
                        code = response?.currentRemote?.condition?.code,
                        icon = response?.currentRemote?.condition?.icon,
                        text = response?.currentRemote?.condition?.text
                    ),
                    feelslikeC = response?.currentRemote?.feelslikeC,
                    feelslikeF = response?.currentRemote?.feelslikeF,
                    gustKph = response?.currentRemote?.gustKph,
                    gustMph = response?.currentRemote?.gustMph,
                    humidity = response?.currentRemote?.humidity,
                    isDay = response?.currentRemote?.isDay,
                    lastUpdated = response?.currentRemote?.lastUpdated,
                    lastUpdatedEpoch = response?.currentRemote?.lastUpdatedEpoch,
                    precipIn = response?.currentRemote?.precipIn,
                    precipMm = response?.currentRemote?.precipMm,
                    pressureIn = response?.currentRemote?.pressureIn,
                    pressureMb = response?.currentRemote?.pressureMb,
                    tempC = response?.currentRemote?.tempC,
                    tempF = response?.currentRemote?.tempF,
                    uv = response?.currentRemote?.uv,
                    visKm = response?.currentRemote?.visKm,
                    visMiles = response?.currentRemote?.visMiles,
                    windDegree = response?.currentRemote?.windDegree,
                    windDir = response?.currentRemote?.windDir,
                    windKph = response?.currentRemote?.windKph,
                    windMph = response?.currentRemote?.windMph,
                ), location = LocationLocal(
                    country = response?.locationRemote?.country,
                    lat = response?.locationRemote?.lat,
                    localtime = response?.locationRemote?.localtime,
                    localtimeEpoch = response?.locationRemote?.localtimeEpoch,
                    lon = response?.locationRemote?.lon,
                    name = response?.locationRemote?.name,
                    region = response?.locationRemote?.region,
                    tzId = response?.locationRemote?.tzId
                )
            )
        )
    } else{
        ResponseWrapper.Error(this.errorBody()?.string().orEmpty(), this.code())
    }
}