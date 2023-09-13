package com.example.apiauthtest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {
    val result = MutableLiveData<WeatherData?>()

    fun getWeather(){

        viewModelScope.launch(IO){
            var response = WeatherRepo.getWeather()
            if (response?.isSuccessful == true) {
                val currentData = response.body()?.current
                val locationData = response.body()?.location
                val weatherData = WeatherData(current = currentData, location = locationData)
                result.postValue(weatherData)
                Log.i("FETCHED:", "$currentData")
            } else {
                Log.e("NETWORK ERROR","Network Call failed")
            }
        }

    }
}