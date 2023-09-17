package com.example.apiauthtest.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiauthtest.data.Location
import com.example.apiauthtest.data.WeatherData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {
    val result = MutableLiveData<WeatherData?>()
    private var currentQuery = ""

    fun updateQuery(newQuery: String){
        currentQuery = newQuery
        getWeather()
    }

    fun getWeather() {
        viewModelScope.launch(IO){
            var response = WeatherRepo.getWeather(currentQuery, "it")
            if (response?.isSuccessful == true) {
                val currentData = response.body()?.current
                val locationData = response.body()?.location
                val weatherData = WeatherData(current = currentData, location = locationData)
                result.postValue(weatherData)
                Log.i("CURRENT DATA:", "$currentData")
                Log.i("LOCATION DATA:", "$locationData")

            } else {
                Log.e("NETWORK ERROR","Network Call failed")
            }
        }
    }
}