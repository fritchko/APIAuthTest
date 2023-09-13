package com.example.apiauthtest

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherEndpoint {
    @GET("current.json")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("key") apiKey: String,
        @Query("lang") language: String
    ): Response<WeatherData>
}