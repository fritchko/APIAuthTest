package com.example.apiauthtest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRepo {

    var weatherEndpoint: WeatherEndpoint? = null

    suspend fun getWeather(): Response<WeatherData>? {
        if (weatherEndpoint == null) {
            weatherEndpoint = createRetrofitInstance().create(WeatherEndpoint::class.java)
        }

        return weatherEndpoint?.getWeather("Naro IT", "9c8c0eb6b72342dfb6435321231309","it")
    }


    fun createRetrofitInstance(): Retrofit {

        val baseUrl = "https://api.weatherapi.com/v1/"
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}