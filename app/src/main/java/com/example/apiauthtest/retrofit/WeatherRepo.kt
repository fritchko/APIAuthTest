package com.example.apiauthtest.retrofit

import com.example.apiauthtest.data.WeatherData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRepo {

    var weatherEndpoint: WeatherEndpoint? = null

    suspend fun getWeather(query: String, language: String): Response<WeatherData>? {
        if (weatherEndpoint == null) {
            weatherEndpoint = createRetrofitInstance().create(WeatherEndpoint::class.java)
        }

        return weatherEndpoint?.getWeather(query,language)
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