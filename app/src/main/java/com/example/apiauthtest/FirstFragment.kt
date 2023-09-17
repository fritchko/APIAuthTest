package com.example.apiauthtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.apiauthtest.databinding.FragmentFirstBinding
import com.example.apiauthtest.retrofit.WeatherViewModel
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            val newQuery = binding.editTextFirst.text.toString()
            weatherViewModel.updateQuery(newQuery)
            // The order of execution matters here
            observeLiveData()
            weatherViewModel.getWeather()
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun observeLiveData() {
        weatherViewModel.result.observe(viewLifecycleOwner) { weatherData ->
            val currentData = weatherData?.current
            val locationData = weatherData?.location

            if (currentData != null) {
                binding.weatherText.text = "Temperatura: ${currentData.tempC?.toInt()}°"
                binding.conditionText.text = currentData.condition?.text
                binding.windTv.text = "${currentData.windDir} ${currentData.windKph}km/h"

                val iconUrl = currentData.condition?.icon

                // Check if the URL is relative (starts with "//")
                val imageUrl = if (iconUrl?.startsWith("//") == true) {
                    "https:$iconUrl"
                } else {
                    iconUrl
                }

                Picasso.get()
                    .load(imageUrl)
                    .error(com.google.android.material.R.drawable.mtrl_ic_error)
                    .into(binding.weatherImage)
            } else {
                binding.weatherText.text = "No data available."
            }

            if (locationData != null) {
                binding.locationText.text = "${locationData.name}, ${locationData.region}"
            }
        }
    }
}