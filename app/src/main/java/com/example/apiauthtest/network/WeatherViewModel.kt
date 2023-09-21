package com.example.apiauthtest.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiauthtest.data.local.WeatherDataLocal
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class WeatherViewModel(): ViewModel() {
    private val _result = MutableLiveData<WeatherDataLocal?>()
    val result: LiveData<WeatherDataLocal?>
        get() = _result
    private var currentQuery = ""

    fun updateQuery(newQuery: String){
        currentQuery = newQuery
        getWeather()
    }

    fun getWeather() {
        viewModelScope.launch(IO){
            var response = WeatherRepo.getWeather(currentQuery, "it")
            if (response is ResponseWrapper.Success) {
                val currentData = response.value?.current
                val locationData = response.value?.location
                val weatherDataLocal = WeatherDataLocal(current = currentData, location = locationData)
                _result.postValue(weatherDataLocal)
                Log.i("CURRENT DATA:", "$currentData")
                Log.i("LOCATION DATA:", "$locationData")

            } else {
                Log.e("NETWORK ERROR","Network Call failed")
            }
        }
    }
}