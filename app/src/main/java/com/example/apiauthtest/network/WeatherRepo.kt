package com.example.apiauthtest.network

import com.example.apiauthtest.data.local.WeatherDataLocal
import com.example.apiauthtest.data.remote.toWeatherDataLocal
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRepo {

    var weatherEndpoint: WeatherEndpoint? = null

    suspend fun getWeather(query: String, language: String): ResponseWrapper<WeatherDataLocal>? {
        if (weatherEndpoint == null) {
            weatherEndpoint = createRetrofitInstance().create(WeatherEndpoint::class.java)
        }

        val response = weatherEndpoint?.getWeather(query,language)

        return response?.toWeatherDataLocal()
    }


    fun createRetrofitInstance(): Retrofit {

        val baseUrl = "https://api.weatherapi.com/v1/"

        val loggingInterceptor = HttpLoggingInterceptor()
        val authInterceptor = AuthInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}