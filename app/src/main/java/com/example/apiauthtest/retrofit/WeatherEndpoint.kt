package com.example.apiauthtest.retrofit

import com.example.apiauthtest.data.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherEndpoint {
    @GET("current.json")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("key") apiKey: String,
        @Query("lang") language: String
    ): Response<WeatherData>
}