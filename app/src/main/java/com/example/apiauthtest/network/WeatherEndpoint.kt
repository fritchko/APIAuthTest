package com.example.apiauthtest.network

import com.example.apiauthtest.data.remote.WeatherDataRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherEndpoint {
    @GET("current.json")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("lang") language: String
    ): Response<WeatherDataRemote>
}