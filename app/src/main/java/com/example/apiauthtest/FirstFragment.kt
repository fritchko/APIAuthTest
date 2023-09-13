package com.example.apiauthtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apiauthtest.databinding.FragmentFirstBinding

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

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.submitButton.setOnClickListener {
            weatherViewModel.getWeather()
        }

        observeLiveData()



    }

    fun observeLiveData(){
        weatherViewModel.result.observe(viewLifecycleOwner) { weatherData ->
            val currentData = weatherData?.current
            val locationData = weatherData?.location

            if (currentData != null){
                binding.weatherText.text = "Temperatura: ${currentData.tempC}°"
            } else{
                binding.weatherText.text = "No data available."
            }

            if (locationData != null){
                binding.locationText.text = "${locationData.name}, ${locationData.region}"
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}